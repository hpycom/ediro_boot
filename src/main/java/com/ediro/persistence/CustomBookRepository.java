package com.ediro.persistence;


import java.util.List;

import com.ediro.domain.Book;
import com.ediro.vo.BookVO;

public interface CustomBookRepository {
	public List<Book> search(BookVO vbook);
}
