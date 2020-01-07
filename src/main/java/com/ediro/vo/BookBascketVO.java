package com.ediro.vo;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBascketVO {
	private BigInteger bookCode;
	private String bookTitle;
	private String barcode;
	private String author;
	private String publisher;
	private String pubDate;
	private int OrderQty;
}
