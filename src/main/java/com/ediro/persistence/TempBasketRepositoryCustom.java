package com.ediro.persistence;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.CusBasketVO;

public interface TempBasketRepositoryCustom {
	public List<CusBasketVO> search(@AuthenticationPrincipal EdiroSecurityUser user);
}
