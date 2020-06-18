package com.ediro.vo;

import java.math.BigInteger;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ediro.domain.BookStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookSchRstVO {
	private BigInteger bookCode;
	private String bookTitle;
	private String author;
	private String publisher;
	private String pubDate;
	private int price;
	private String barcode;
	private int OrderQty;
	private int salePercent;

    private String bookstatus;
}
