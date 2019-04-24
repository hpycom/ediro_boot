package com.ediro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/hpycom
 */
@Getter
@Setter
@Entity
@Table(name="tbl_member_roles")
@IdClass(MemberRoleId.class)
@ToString
public class MemberRole {
	
    @Id
    @ManyToOne
    @JoinColumn(name="memberID")
    private Member member;
    
    @Id
    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles roles;
    
   
}
