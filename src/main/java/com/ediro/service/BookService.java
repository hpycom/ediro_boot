package com.ediro.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BookRepository;
import com.ediro.persistence.MemberRepository;
import com.ediro.vo.BooksVO;
import com.ediro.vo.PageVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	MemberRepository memberRepository;
	
	public List<Book> getBooks(String memberid){
		 List<Book> bookList =	(List<Book>) bookRepository.findByMember_MemberID(memberid);
		 return bookList;
	}
	
	public void save(Book book,Principal principal)
	{
		Optional<Member> member = memberRepository.findById(principal.getName());
		book.setMember(member.get());
		bookRepository.save(book);
	}
	
	public void save(BooksVO books,Principal principal)
	{
		Optional<Member> member = memberRepository.findById(principal.getName());
		//List<Book> lstBooks = books.getData();
		
		for(Book book :books.getData())
		{
			book.setMember(member.get());
			bookRepository.save(book);
		}
	}
	
	public Page<Book> getBooks(PageVO vo,Book book)
	{
		Pageable page = vo.makePageable(0, "bookCode");
		
		Page<Book> result = bookRepository.findAll(bookRepository.makePredicate(book), page);
		
		return result;
	}
}
