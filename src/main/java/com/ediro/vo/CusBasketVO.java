package com.ediro.vo;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CusBasketVO {
	private int checkbox;
	private BigInteger bookCode;
	private String bookTitle;
	private String barcode;
	private String author;
	private String publisher;
	private String pubDate;
    
	private int orderQty;
	private LocalDateTime regdate;
}
