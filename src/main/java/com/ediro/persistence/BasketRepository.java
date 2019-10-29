package com.ediro.persistence;



import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ediro.domain.Basket;


public interface BasketRepository extends CrudRepository<Basket, String>, QuerydslPredicateExecutor<Basket>
{
	public Basket findOneByBook_bookCodeAndMember_memberID(String book_code,String MemberID);
}
