package com.atguigu.serviceucenter.mapper;

import com.atguigu.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-11-25
 */

@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegister(String day);

}
