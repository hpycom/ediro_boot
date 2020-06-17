package com.ediro.domain;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/*@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, String>  {

	  @Override
	    public String convertToDatabaseColumn(BookStatus bookstatus) {
	        if (bookstatus == null) {
	            return null;
	        }
	        return bookstatus.getCode();
	    }
	 
	    @Override
	    public BookStatus convertToEntityAttribute(String code) {
	        if (code == null) {
	            return null;
	        }
	 
	        return Stream.of(BookStatus.values())
	          .filter(c -> c.getCode().equals(code))
	          .findFirst()
	          .orElseThrow(IllegalArgumentException::new);
	    }
}
*/