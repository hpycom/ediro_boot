package com.ediro.service;

import java.math.BigInteger;
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
	
	public List<Book> getBooks(){
		// List<Book> bookList =	(List<Book>) bookRepository.findByMember_MemberID(memberid);
		 List<Book> bookList =	(List<Book>) bookRepository.findAll();
		 return bookList;
	}
	
	public void save(Book book,Principal principal)
	{
		Optional<Member> member = memberRepository.findByMemberID(principal.getName());
		book.setMember(member.get());
		bookRepository.save(book);
	}
	
	public void save(BooksVO books,Principal principal)
	{
		Optional<Member> member = memberRepository.findByMemberID(principal.getName());
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
	
	public Book getBookByBookCode(long book_code )
	{
		  Book book  = bookRepository.findBybookCode(book_code);
		  return book;
	}
	
	public void deleteBook(long book_code,Principal principal)
	{
		  Book book  = bookRepository.findBybookCode(book_code);
		  book.setDelYN("Y");
		  bookRepository.save(book);
	}
	
}
