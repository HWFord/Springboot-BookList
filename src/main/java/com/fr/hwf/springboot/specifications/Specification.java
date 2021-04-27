//package com.fr.hwf.springboot.specifications;
//
//import java.util.function.Predicate;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//public interface Specification<T> {
//	  Predicate<T> toPredicate(Root<T> root, CriteriaQuery<T> query, CriteriaBuilder builder);
//}