package com.fr.hwf.springboot.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.hwf.springboot.entites.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

//	@Query(value="select * from book b where CONCAT(b.name, '') like %:keyword%"
//			+"OR CONCAT(b.price, '') like %:keyword%"
//			+"OR CONCAT(b.nbpages, '') like %:keyword%"
//			, nativeQuery= true)
//	List<Book> searchBooks(String keyword);

		@Query("SELECT b FROM Book b where b.name like '%:keyword%'")
		List<Book> keywordSearch(@Param("keyword") String keyword);

		@Query("SELECT b FROM Book b where b.nbpages <= :number1")
		List<Book> pageLessThan(@Param("number1") int number1);

		@Query("SELECT b FROM Book b where b.nbpages >= :number2")
		List<Book> pageMoreThan(@Param("number2") int number2);

		@Query("SELECT b FROM Book b where b.price <= :amount1")
		List<Book> priceSmallerThan(@Param("amount1") double amount1);

		@Query("SELECT b FROM Book b where b.price >= :amount2")
		List<Book> priceBiggerThan(@Param("amount2")double amount2);


}
