package com.ediro.persistence;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ediro.domain.Book;




/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/
 */
public interface BookRepository extends CrudRepository<Book, String> {

	public List<Book> findByBookTitleContaining(String title);
	public List<Book> findByMember_MemberID(String memberID);
}