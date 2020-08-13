package com.ediro.vo;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CusTempBasketVO {
	private BigInteger bookCode;
	private String bookTitle;
	private String barcode;
	private String author;
	private String publisher;
	private String pubDate;
    private int price;
    
	private int salePercent;
	private int qty;
	private LocalDateTime regdate;
}
