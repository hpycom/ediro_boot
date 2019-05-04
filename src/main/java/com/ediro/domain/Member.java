package com.ediro.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.UpdateTimestamp;
import com.ediro.domain.MemberRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/
 */
@Getter
@Setter
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="memberID")
@ToString
public class Member {


    @Id
 	private String memberID;
	private String memberPwd;
	private String companyName;
	private String bossName;
	private String bizRegNo;
	private String postno;
	private String address;
	private String address_detail;
	private String phone;
	private String phone_2;
	private String email;
	private String homepage;
	private String fax;
	
	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<Book>();
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
	@OneToMany(mappedBy="member")
	private List<MemberRole> memberRoles;
	
	public void addBook(Book book)
	{
		this.books.add(book);
		if(book.getMember() != this)
		{
			book.setMember(this);
		}
	}
	
}

