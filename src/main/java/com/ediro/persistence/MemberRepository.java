package com.ediro.persistence;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ediro.domain.Member;

/**
 * @author ediro
 * @Date 2017-12-24
 * @GitHub : https://github.com/
 */
@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
 public Optional<Member> findByMemberID(String memberID);
 public Member findMemberByMemberID(String name);
}
