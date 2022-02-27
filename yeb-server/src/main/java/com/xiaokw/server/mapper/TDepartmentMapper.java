package com.xiaokw.server.mapper;

import com.xiaokw.server.entity.TDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface TDepartmentMapper extends BaseMapper<TDepartment> {

    List<TDepartment> getAllDepartment(@Param("parentId") Integer parentId);

    void addDep(TDepartment department);

    void deleteDep(TDepartment tDepartment);
}
