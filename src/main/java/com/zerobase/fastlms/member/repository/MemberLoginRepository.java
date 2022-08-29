package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberLoginRepository extends JpaRepository<MemberLoginHistory, String> {


    Optional<List<MemberLoginHistory>> findByUserId(String userId);
}
