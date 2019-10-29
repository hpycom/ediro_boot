package com.ediro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.persistence.CustomBookRepository;
import com.ediro.vo.BookVO;

@Service
public class BookServiceCustom {

	@Autowired
	CustomBookRepository bookReposit;
	
	public List<Book> getBooks(BookVO vbook){
		 List<Book> bookList =	(List<Book>) bookReposit.search(vbook);
		 return bookList;
	}
}
