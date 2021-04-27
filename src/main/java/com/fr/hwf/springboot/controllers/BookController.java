package com.fr.hwf.springboot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.hwf.springboot.dtos.BookDto;
import com.fr.hwf.springboot.entites.Book;
import com.fr.hwf.springboot.entites.User;
import com.fr.hwf.springboot.repositories.BookRepository;
import com.fr.hwf.springboot.service.BookService;
import com.fr.hwf.springboot.service.UserService;

@Controller(value = BookController.BASE_ROUTE)
@RequestMapping(BookController.BASE_ROUTE)
public class BookController extends BaseCrudController<Book, BookDto>{

	public static final String TEMPLATE_NAME = "book";
    public static final String BASE_ROUTE = "book";

    public BookController() {
        super(TEMPLATE_NAME);
    }

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public BookController(String templateName) {
		super(templateName);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void preCreateGet(final Model model) {
        model.addAttribute("users", this.userService.getTemplateList());
    }

    @Override
    protected Book preCreatePost(BookDto dto) throws Exception {
        Book book = new Book();
        book.setName(dto.getName());
        book.setNbpages(dto.getNbpages());
        book.setPrice(dto.getPrice());

        if (dto.getUserId() != null) {
            User user = this.userService.findUser(dto.getUserId());
            if (user == null) {
                throw new Exception("User cannot be found");
            }
            book.setUser(user);
        }

        return book;
    }

}
