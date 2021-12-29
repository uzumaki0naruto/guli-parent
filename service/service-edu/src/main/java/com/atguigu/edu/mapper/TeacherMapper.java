package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-08-16
 */
@Repository

public interface TeacherMapper extends BaseMapper<Teacher> {

}
