package com.ediro.persistence;


import org.springframework.data.repository.CrudRepository;
import com.ediro.domain.Member;

/**
 * @author ediro
 * @Date 2017-12-24
 * @GitHub : https://github.com/
 */
public interface MemberRepository extends CrudRepository<Member, String> {

}
