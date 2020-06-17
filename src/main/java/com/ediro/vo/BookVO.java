package com.ediro.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO {
	private int chk;
	private String bookTitle;
	private String barcode;
	private String author;
	private String publisher;
	private String pubDate;
	private String startPubdate;
	private String endPubdate;
	private String bookstatus;
}
