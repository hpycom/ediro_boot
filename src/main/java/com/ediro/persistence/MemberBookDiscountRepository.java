package com.ediro.persistence;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


import com.ediro.domain.Book;

import com.ediro.domain.MemberBookDiscount;
import com.google.common.base.Optional;
public interface MemberBookDiscountRepository extends CrudRepository<MemberBookDiscount, String> {

	public List<MemberBookDiscount> findByBook(Book book);
	
	public List<MemberBookDiscount> findByBook_bookCode(BigInteger book_code);
	
	public Optional<MemberBookDiscount> findByBook_bookCodeAndMember_mid(BigInteger book_code,int mid);
}
