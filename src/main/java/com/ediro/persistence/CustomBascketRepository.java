package com.ediro.persistence;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ediro.domain.Basket;
import com.ediro.domain.Member;
import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.CusBasketVO;;


public interface CustomBascketRepository {
	public List<CusBasketVO> search(@AuthenticationPrincipal EdiroSecurityUser user);
	
}

