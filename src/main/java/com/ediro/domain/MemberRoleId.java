package com.ediro.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class MemberRoleId implements Serializable {

	 private int member;
	 private int roles;
}
