package com.ediro.persistence;


import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ediro.domain.TempBasket;




public interface TempBasketRepository extends JpaRepository<TempBasket, String>,TempBasketRepositoryCustom{
	
	TempBasket findOneByBook_bookCodeAndMember_mid(long bookCode, int mid);
	List<TempBasket> findAllByMember_mid(int mid);
	
}
