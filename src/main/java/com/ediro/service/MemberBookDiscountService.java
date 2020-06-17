package com.ediro.service;

import com.ediro.vo.MemberBookDiscountVO;
import com.ediro.vo.MemberBookDiscountsVO;
import com.google.common.base.Optional;
import com.ediro.domain.MemberBookDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.ediro.domain.Book;
import com.ediro.domain.Member;
import com.ediro.persistence.BookRepository;
import com.ediro.persistence.CustomMemberRepository;
import com.ediro.persistence.MemberBookDiscountRepository;
import com.ediro.persistence.MemberRepository;
import com.ediro.security.EdiroSecurityUser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberBookDiscountService {
  @Autowired
  MemberBookDiscountRepository memBookDisRepo;
  
  @Autowired
  MemberRepository cMemRepo;
  
  @Autowired
  BookRepository bookRepo;
  
  public List<MemberBookDiscountVO> getMembookDiscounts(BigInteger book_code )
  {
	 List<MemberBookDiscount> memBookdiscounts = (List<MemberBookDiscount>) memBookDisRepo.findByBook_bookCode(book_code);
	 List<MemberBookDiscountVO> membookDisVos = new ArrayList<MemberBookDiscountVO>();
	 memBookdiscounts.forEach(mbookDis ->{ 
		 
		 MemberBookDiscountVO mbookDisCntVo = new MemberBookDiscountVO();
		 	
		    mbookDisCntVo.setDiscountPct( mbookDis.getDiscountPct());
		 	mbookDisCntVo.setAddress(mbookDis.getMember().getAddress());
		 	mbookDisCntVo.setAddress_detail(mbookDis.getMember().getAddress_detail());
		 	mbookDisCntVo.setBossName(mbookDis.getMember().getBossName());
		 	mbookDisCntVo.setCompanyName(mbookDis.getMember().getCompanyName());
		 	mbookDisCntVo.setMid(mbookDis.getMember().getMid());
		 	membookDisVos.add(mbookDisCntVo);
		 	
		 
	 });
	 
	 return membookDisVos;
  }
  
  public void saveMembookDiscounts(BigInteger book_code,MemberBookDiscountsVO memBookDiscounts)
  {
	  for (MemberBookDiscountVO mbookDic : memBookDiscounts.getData()) {
		  
		  Member mem = cMemRepo.findMemberByMid(mbookDic.getMid());
		  Book book = bookRepo.findBybookCode(book_code);
		 
		  MemberBookDiscount mBookDis = new MemberBookDiscount();
		  mBookDis.setBook(book);
		  mBookDis.setMember(mem);
		  mBookDis.setDiscountPct(mbookDic.getDiscountPct());
		  
		  memBookDisRepo.save(mBookDis);
		
	}
	 
  }
  
  public void DelMembookDiscounts(BigInteger book_code,int mid)
  {
	  Optional<MemberBookDiscount> mbookDiscnt =  memBookDisRepo.findByBook_bookCodeAndMember_mid(book_code, mid);
	  
	  if (mbookDiscnt.isPresent()){
		  MemberBookDiscount foo = mbookDiscnt.get();
		  memBookDisRepo.delete(foo);
	  }
	  
	 
  }
  
  public boolean getMemberBookDisCount(BigInteger book_code,int mid)
  {
	  Optional<MemberBookDiscount> mbookDis =  memBookDisRepo.findByBook_bookCodeAndMember_mid(book_code, mid);
	 
	  if(mbookDis.isPresent())
		  return true;
	  else 
		  return false;
	 
  }
  
  
  
  public void saveMemBookDiscount(BigInteger book_code,int mid,int discount)
  {
	  Member mem = cMemRepo.findMemberByMid(mid);
	  Book book = bookRepo.findBybookCode(book_code);
	 
	  MemberBookDiscount mBookDis = new MemberBookDiscount();
	  mBookDis.setBook(book);
	  mBookDis.setMember(mem);
	  mBookDis.setDiscountPct(discount);
	  
	  memBookDisRepo.save(mBookDis);
	  
  }
}
