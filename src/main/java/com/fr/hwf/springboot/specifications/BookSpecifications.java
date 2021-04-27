//package com.fr.hwf.springboot.specifications;
//
//import java.util.List;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.Query;
//
//import com.fr.hwf.springboot.entites.Book;
//import com.fr.hwf.springboot.entites.BookSearch;

//public class BookSpecifications implements Specification<Book>{

//	public static Specification<Book> keywordSearchBook(){
//		return new Specification<Book>() {
//
//			@Override
//			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				@Query(value="select * from book b where CONCAT(b.name, '') like %:keyword%"
//				+"OR CONCAT(b.price, '') like %:keyword%"
//				+"OR CONCAT(b.nbpages, '') like %:keyword%"
//				, nativeQuery= true)
//				List<Book> books;
//				return null;
//			}
//
//		}
//	}


//	private BookSearch criteria;
//	public static Specification<Book> getBooksByNameSpec(String name) {
//	      return new Specification<Book>() {
//
//			@Override
//			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//	              Predicate equalPredicate = criteriaBuilder.equal(root.get(Book.name, name);
//	              return equalPredicate;
//				return null;
//			}
//	      };
//	  }

//	@Override
//	public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//}
