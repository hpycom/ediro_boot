package com.ediro.domain.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
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
@DynamicUpdate
@Table(name="tbl_basicBoard")
@EqualsAndHashCode(of="bno")
@ToString
public class BasicBoard {
	 	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long bno;
	 	private String title;
	 	
	 	private String writer;
	 	
	 	private String content;
	 	
	 	@CreationTimestamp
	 	private Timestamp regdate;
	 	@UpdateTimestamp
	 	private Timestamp updatedate;
}
