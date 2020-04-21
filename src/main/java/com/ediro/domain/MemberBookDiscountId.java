package com.ediro.domain;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Data;

@Data
public class MemberBookDiscountId implements Serializable {
	 private int member;
	 private BigInteger book;
}
