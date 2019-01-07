package com.ediro.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.UpdateTimestamp;
import com.ediro.domain.MemberRole;

import javax.persistence.*;
import java.time.LocalDateTime;
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
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
	   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn(name="memberRole")
	    private List<MemberRole> roles;
}

