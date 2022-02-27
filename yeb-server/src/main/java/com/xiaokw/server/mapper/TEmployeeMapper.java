package com.xiaokw.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaokw.server.entity.TEmployee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface TEmployeeMapper extends BaseMapper<TEmployee> {

    /**
     * 获取所有员工（分页）
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    IPage<TEmployee> getEmployeeByPage(Page<TEmployee> page, @Param("employee") TEmployee employee, @Param("beginDateScope") LocalDate[] beginDateScope);
}
