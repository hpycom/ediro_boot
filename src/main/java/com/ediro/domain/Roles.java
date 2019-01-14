package com.ediro.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;

/**
 * @author hpycom
 * @Date 2017-12-24
 * @GitHub : https://github.com/hpycom
 */
@Getter
@Setter
@Entity
@Table(name="tbl_roles")
public class Roles {
    @Id@Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    private String roleName;
    
    @OneToMany(mappedBy="member")
    private List<MemberRole> members;
}
