package com.ediro.persistence;


import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ediro.domain.Book;

import com.ediro.vo.BookBascketVO;
import com.ediro.vo.BookVO;


import org.springframework.data.domain.Pageable;


public interface CustomBookRepository {
	public List<Book> search(BookVO vbook);
	public Page<BookBascketVO> getBookListWithCartInfo(BookVO bookvo,Pageable page,Principal principal);
}
