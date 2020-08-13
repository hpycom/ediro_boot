package com.ediro.vo;

import java.math.BigInteger;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TempBasketVO {
	private int mid;
	
	private BigInteger bookCode;
	private int salePercent;
	private int qty;
}
