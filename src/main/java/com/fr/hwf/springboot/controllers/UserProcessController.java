package com.fr.hwf.springboot.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fr.hwf.springboot.dtos.BookDto;
import com.fr.hwf.springboot.dtos.KeywordDto;
import com.fr.hwf.springboot.entites.Book;
import com.fr.hwf.springboot.entites.Role;
import com.fr.hwf.springboot.entites.User;
import com.fr.hwf.springboot.repositories.BookRepository;
import com.fr.hwf.springboot.service.BookService;
import com.fr.hwf.springboot.service.UserService;

@Controller
@RequestMapping(UserProcessController.BASE_ROUTE)

public class UserProcessController {

	public String TEMPLATE_NAME = "process";
    public static final String BASE_ROUTE = "process";

    protected static final String FLASH_ERRORS = "errors";
	protected static final String INDEX_ROUTE = "/index";
	protected static final String CUSTOMER_TEMPLATE = "/customer";
	protected static final String SELLER_TEMPLATE = "/seller";
	protected static final String ADDBOOK_TEMPLATE = "/create";
	protected static final String KEYWORD_SEARCH_TEMPLATE = "/nameSearch";
	protected static final String PRICE_SEARCH_TEMPLATE = "/priceSearch";
	protected static final String NBPAGES_SEARCH_TEMPLATE = "/pagesSearch";

    @GetMapping(value = {"/","/login"})
    public String welcome(HttpServletResponse response) {
    return "welcome";
    }

    @Autowired
	private UserService userService;

    @Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	//Display customer or seller template when logged in
	//for customer display list of books owned by seller
	//for seller displayed owned books, allow user to add books

    @RequestMapping(value = {"/login/{id}"}, method = RequestMethod.GET)
    public String login(@PathVariable final Long id, final Model model) {
        String result = "redirect:" + "/user";

        User person = this.userService.findUser(id);

        if (person != null) {
        	Role userRole = person.getRole();
        	if(userRole.getId() == 1) {
        		result = "/" + this.TEMPLATE_NAME + CUSTOMER_TEMPLATE;
        		model.addAttribute("books", this.bookService.findSellerBooks(""));
        	}else if (userRole.getId() == 2) {
        		result = "/" + this.TEMPLATE_NAME + SELLER_TEMPLATE;
        		Long userId = person.getId();
        		model.addAttribute("books", this.bookService.findAUsersBooks(userId));
        	}
            model.addAttribute("person", person);
        }

        return result;
    }

    //for user customer allow to buy books from seller
    @RequestMapping(value = {"/buy/{userId}+{bookId}"}, method = RequestMethod.GET)
    public String buy(@PathVariable final Long userId,@PathVariable final Long bookId, final Model model) throws Exception {

        try {
        	Book book = this.bookService.findBook(bookId);
        	User user = this.userService.findUser(userId);
        	book.setUser(user);
        	this.bookRepository.save(book);
        }catch(Exception e) {
        	throw new Exception("User cannot buy book");
        }

        return "redirect:" + "/user/details/" + userId;
    }

    //opens process create when seller clicks on add book
    @RequestMapping(value = {"/addBook/{userId}"}, method = RequestMethod.GET)
    public String addBook(@PathVariable final Long userId, final Model model) throws Exception {

    	User person = this.userService.findUser(userId);
        model.addAttribute("person", person);

        return "/" + this.TEMPLATE_NAME + ADDBOOK_TEMPLATE;
    }

    //get info from add book form a send to createPost to add new book
    protected Book preCreatePost(BookDto dto, @PathVariable final Long userId) throws Exception{
    	Book book = new Book();
        book.setName(dto.getName());
        book.setNbpages(dto.getNbpages());
        book.setPrice(dto.getPrice());

        User user = userService.findUser(userId);
        book.setUser(user);
        return book;
    }

    //add a book owned by the seller who adds it, get info from preCreatePost
    @RequestMapping(value = {"/addBook/{userId}"}, method = RequestMethod.POST)
    public String createPost(BookDto dto, @PathVariable final Long userId, final RedirectAttributes attributes) throws Exception {
    	String result = "redirect:" + "/process/login/" + userId;

        try {
            Book book = this.preCreatePost(dto, userId);
            this.bookRepository.save(book);
        } catch (Exception e) {
            attributes.addFlashAttribute(FLASH_ERRORS, e.getMessage());
            result = "/" + this.TEMPLATE_NAME + ADDBOOK_TEMPLATE;
        }

        return result;
    }

    @RequestMapping(value = {"/login/{userId}/nameSearch"}, method = RequestMethod.GET)
    public String keywordSearch(@PathVariable final Long userId, final Model model,@RequestParam(required=false) String name) {
    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

    	return  this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
    }

    @RequestMapping(value = {"/login/{userId}/nameSearch"}, method = RequestMethod.POST)
    public String keywordSearchResult(@PathVariable final Long userId, final Model model, @RequestParam(required=false) String name) {
    	String result = "redirect:" + "/process/login/" + userId;

    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

	    try {
	    	if (name!= null) {
 				List<Book> books = bookService.filterSellerBooksName(name);
 				model.addAttribute("keyword", name);
		    	model.addAttribute("books", books);
		    	result = this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
	    	}else if(name.isBlank()) {
	    		model.addAttribute("errorMessage", "this field cannot be empty");
	    		result = this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	        result = this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
	    }

    	return result;
    }

    @RequestMapping(value = {"/login/{userId}/priceSearch"}, method = RequestMethod.GET)
    public String priceSearch(@PathVariable final Long userId, final Model model) {
    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

    	return this.TEMPLATE_NAME +PRICE_SEARCH_TEMPLATE;
    }


    @RequestMapping(value = {"/login/{userId}/priceSearch"}, method = RequestMethod.POST)
    public String priceSearchResult(@PathVariable final Long userId, final Model model,
    		@RequestParam(required=false) double price, @RequestParam("priceFilter") String option ) {

    	String result = "redirect:" + "/process/login/" + userId;

    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

	    try {
	    	if (option == "bigger") {
 				List<Book> books = bookService.filterPriceBiggerThan(price);
 				model.addAttribute("price", price);
		    	model.addAttribute("books", books);
		    	result = this.TEMPLATE_NAME +PRICE_SEARCH_TEMPLATE;
	    	}else if (option == "smaller"){
	    		List<Book> books = bookService.filterPriceSmallerThan(price);
 				model.addAttribute("price", price);
		    	model.addAttribute("books", books);
		    	result = this.TEMPLATE_NAME +PRICE_SEARCH_TEMPLATE;
	    	}else {
	    		model.addAttribute("errorMessage", "this field cannot be empty");
	    		result = this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	        result = this.TEMPLATE_NAME + KEYWORD_SEARCH_TEMPLATE;
	    }
    	return result;
    }

    @RequestMapping(value = {"/login/{userId}/nbpagesSearch"}, method = RequestMethod.GET)
    public String nbpagesSearch(@PathVariable final Long userId, final Model model) {
    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

    	return this.TEMPLATE_NAME +NBPAGES_SEARCH_TEMPLATE;
    }

    @RequestMapping(value = {"/login/{userId}/nbpagesSearch"}, method = RequestMethod.POST)
    public String nbpagesSearchResult(@PathVariable final Long userId, final Model model,
    		@RequestParam(required=false) Integer pages, @RequestParam("pageFilter") String option ) {

    	String result = "";

    	User person = this.userService.findUser(userId);
    	model.addAttribute("person", person);

    	try {
	    	if (option == "more") {
 				List<Book> books = bookService.filterPagesMoreThan(pages);
 				model.addAttribute("nbpages", pages);
		    	model.addAttribute("books", books);
		    	result = this.TEMPLATE_NAME +NBPAGES_SEARCH_TEMPLATE;
	    	}else if(option == "less"){
	    		List<Book> books = bookService.filterPagesLessThan(pages);
 				model.addAttribute("nbpages", pages);
		    	model.addAttribute("books", books);
		    	result = this.TEMPLATE_NAME +NBPAGES_SEARCH_TEMPLATE;
	    	} else if(option.isEmpty()) {
	    		model.addAttribute("errorMessage", "this field cannot be empty");
	    		result = this.TEMPLATE_NAME +NBPAGES_SEARCH_TEMPLATE;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	        result = this.TEMPLATE_NAME +NBPAGES_SEARCH_TEMPLATE;
	    }
    	return result;
    }





}
