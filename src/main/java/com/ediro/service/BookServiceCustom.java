package com.ediro.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BookRepository;
import com.ediro.persistence.CustomBookRepository;
import com.ediro.persistence.MemberRepository;
import com.ediro.vo.BookVO;
import com.ediro.vo.PageVO;

@Service
public class BookServiceCustom {

	@Autowired
	BookRepository bookReposit;
	@Autowired
	MemberRepository memberRepository;
	
	public List<Book> getBooks(BookVO vbook){
		 List<Book> bookList =	(List<Book>) bookReposit.search(vbook);
		 return bookList;
	}
	
	public void save(Book book,Principal principal)
	{
		Optional<Member> member = memberRepository.findByMemberID(principal.getName());
		book.setMember(member.get());
		bookReposit.save(book);
		
		
	}
	
}
