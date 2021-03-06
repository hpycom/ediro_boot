package com.ediro.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="tbl_tempbasket")

@ToString
@IdClass(TempBasketId.class)
public class TempBasket {
	@Id
	@ManyToOne
	@JoinColumn(name="mid")
	@JsonIgnore
	private Member member;
	
	@Id
	@ManyToOne
	@JoinColumn(name="book_code")
	private Book book;
	
	private int salePercent;
	private int orderQty;
	
	@CreationTimestamp
	private LocalDateTime regdate;
}
