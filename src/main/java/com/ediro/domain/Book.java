package com.ediro.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hpycom
 * @Date 2018-08-21
 * @GitHub : https://github.com/
 */
@Getter
@Setter
@Entity
@Table(name="tbl_book")
@EqualsAndHashCode(of="bookCode")
@ToString
public class Book {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookCode;
	private String bookTitle;
	private String barcode;
	private int boarcodeSubsequence;
	private String author;
	private String publisher;
    private int price;
    
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
}


