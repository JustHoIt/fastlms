package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDto> selectList(MemberParam parameter);
    long selectListCount(MemberParam parameter);

}
