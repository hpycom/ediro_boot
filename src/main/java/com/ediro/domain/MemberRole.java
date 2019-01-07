package com.ediro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/hpycom
 */
@Getter
@Setter
@Entity
@Table(name="tbl_member_roles")
@EqualsAndHashCode(of="fno")
@ToString
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String roleName;
}
