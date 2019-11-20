package com.ediro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="tbl_basket")
@EqualsAndHashCode(of="basket_id")
@ToString
public class Basket {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger basket_id;
	
	@OneToOne
	@JoinColumn(name="mid")
	@JsonIgnore
	private Member member;
	
	@OneToOne
	@JoinColumn(name="book_code")
	private Book book;
	
	private int orderQty;
	
	@CreationTimestamp
	private LocalDateTime regdate;
}
