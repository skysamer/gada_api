package com.smartmobility.gada_api.member.repository;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.type.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmailAndProvider(String email, Provider provider);
    boolean existsByEmailAndProvider(String email, Provider provider);
    Member findByUsername(String name);
}
