package com.ediro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.persistence.BookRepository;
import com.ediro.persistence.CustomBookRepository;
import com.ediro.vo.BookVO;
import com.ediro.vo.PageVO;

@Service
public class BookServiceCustom {

	@Autowired
	BookRepository bookReposit;
	
	public List<Book> getBooks(BookVO vbook){
		 List<Book> bookList =	(List<Book>) bookReposit.search(vbook);
		 return bookList;
	}
	
	
}
