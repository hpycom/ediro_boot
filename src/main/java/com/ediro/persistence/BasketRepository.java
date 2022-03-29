package com.ediro.persistence;






import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.ediro.domain.Basket;


public interface BasketRepository extends CrudRepository<Basket, String>,CustomBascketRepository
{
	public Basket findOneByBook_bookCodeAndMember_memberID(BigInteger book_code,String MemberID); 
	public Basket findOneByBook_bookCodeAndMember_mid(long book_code,int mid);
	
}
