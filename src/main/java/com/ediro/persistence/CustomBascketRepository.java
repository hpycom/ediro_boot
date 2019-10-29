package com.ediro.persistence;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ediro.domain.Basket;
import com.ediro.domain.Member;
import com.ediro.security.EdiroSecurityUser;;


public interface CustomBascketRepository {
	public List<Basket> search(Member member,@AuthenticationPrincipal EdiroSecurityUser user);
}

