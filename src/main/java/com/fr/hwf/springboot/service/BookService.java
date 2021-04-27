package com.fr.hwf.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.h2.engine.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.hwf.springboot.entites.Book;
import com.fr.hwf.springboot.entites.User;
import com.fr.hwf.springboot.repositories.BookRepository;
import com.fr.hwf.springboot.repositories.UserRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private Session session;


	public void save(Book book) {
		this.repository.save(book);
	}

	public Map<Long, String> getTemplateList(){
		Map<Long, String> result = new HashMap<>();

		for (Book item : this.repository.findAll()) {
			result.put(item.getId(), item.getName());
		}

		return result;
	}

	public Map<Long, String> getTemplateListNoUser(){
		Map<Long, String> result = new HashMap<>();

		List<Book> books = this.repository.findAll();

		for (Book item : books) {
			if(item.getUser() == null) {
				result.put(item.getId(), item.getName());
			}
		}

		return result;
	}

	public List<Book> findBooks(final List<Long> ids) {
		return this.repository.findAllById(ids);
	}

	public Book findBook(final Long bookId) {
        return this.repository.findById(bookId).orElse(null);
    }

	public List<Book> findSellerBooks(String keyword){

		List<Book> books = this.repository.findAll();
		List<Book> result = new ArrayList<>();

		List<User> sellers = this.userService.findSellers();
		try {
			for (Book book : books) {
				if(sellers.contains(book.getUser())) {
					result.add(book);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Can not get books only by sellers");
		}

		return result;
	}

	public List<Book> findAUsersBooks(Long userId){

		List<Book> books = this.repository.findAll();
		List<Book> result = new ArrayList<>();
		User user= this.userRepository.getOne(userId);

		try {
			for (Book book : books) {
				if(user == book.getUser()) {
					result.add(book);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Can not get books only for this seller");
		}

		return result;
	}


	 @Autowired
	 EntityManager entityManager;


    public List<Book> filterSellerBooksName(String name) {
    	Query<Book> query = (Query<Book>) entityManager.createQuery("SELECT b FROM Book b where b.name like :keyword", Book.class)
    													.setParameter("keyword", "%"+name+"%");

    	List<Book> books = query.getResultList();

		List<Book> result = new ArrayList<>();

		List<User> sellers = this.userService.findSellers();
			for (Book book : books) {
				if(sellers.contains(book.getUser())) {
					result.add(book);
				}
			}

    	return result;
    }

  public List<Book> filterPagesLessThan(int number) {
  	Query<Book> query = (Query<Book>) entityManager.createQuery("SELECT b FROM Book b where b.nbpages <= :number1", Book.class)
			.setParameter("number1", number);

	List<Book> books = query.getResultList();

	List<Book> result = new ArrayList<>();

	List<User> sellers = this.userService.findSellers();
	for (Book book : books) {
		if(sellers.contains(book.getUser())) {
			result.add(book);
		}
	}

	return result;
  }

	public List<Book> filterPagesMoreThan(int number) {
	  	Query<Book> query = (Query<Book>) entityManager.createQuery("SELECT b FROM Book b where b.nbpages >= :number2", Book.class)
				.setParameter("number2", number);

		List<Book> books = query.getResultList();

		List<Book> result = new ArrayList<>();

		List<User> sellers = this.userService.findSellers();
		for (Book book : books) {
			if(sellers.contains(book.getUser())) {
				result.add(book);
			}
		}

		return result;
	}

public List<Book> filterPriceSmallerThan(double amount) {
  	Query<Book> query = (Query<Book>) entityManager.createQuery("SELECT b FROM Book b where b.price <= :amount1", Book.class)
			.setParameter("amount1", amount);

	List<Book> books = query.getResultList();

	List<Book> result = new ArrayList<>();

	List<User> sellers = this.userService.findSellers();
	for (Book book : books) {
		if(sellers.contains(book.getUser())) {
			result.add(book);
		}
	}

	return result;
}

public List<Book> filterPriceBiggerThan(double amount) {
  	Query<Book> query = (Query<Book>) entityManager.createQuery("SELECT b FROM Book b where b.price >= :amount2", Book.class)
			.setParameter("amount2", amount);

	List<Book> books = query.getResultList();

	List<Book> result = new ArrayList<>();

	List<User> sellers = this.userService.findSellers();
	for (Book book : books) {
		if(sellers.contains(book.getUser())) {
			result.add(book);
		}
	}

	return result;
}




}
