package com.zerobase.fastlms.admin.banner.repository;

import com.zerobase.fastlms.admin.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    Optional<List<Banner>> findByPublicYnOrderBySortNumber(boolean publicYn);
}
