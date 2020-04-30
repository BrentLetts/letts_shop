package com.example.letts_shop.repositories;

import com.example.letts_shop.models.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

    public Member findByUserName(String userName);

    public Member findByEmail(String email);

}
