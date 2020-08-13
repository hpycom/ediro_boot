package com.ediro.persistence;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ediro.security.EdiroSecurityUser;
import com.ediro.vo.CusBasketVO;
import com.ediro.vo.CusTempBasketVO;
import com.ediro.vo.TempBasketVO;

public interface TempBasketRepositoryCustom {
	public List<CusTempBasketVO> search(@AuthenticationPrincipal EdiroSecurityUser user);
}
