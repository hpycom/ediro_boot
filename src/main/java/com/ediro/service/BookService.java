package com.ediro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.persistence.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks(String memberid){
		 List<Book> bookList =	(List<Book>) bookRepository.findByMember_MemberID(memberid);
		 return bookList;
	}
}
