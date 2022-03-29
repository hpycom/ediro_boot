package com.ediro.vo;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketVO {
	private int mid;
		
	private long book_code;
	
	private int salePercent;
	private int orderQty;
	
	
}
