package com.ediro.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBookDiscountVO {

	private int mid;  
	private String companyName;
	private String bossName;
	private String address;
	private String address_detail;
	private int discountPct;
}
