package com.ediro.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDiscountId implements Serializable {
	 private int member;
	 private int book;
}
