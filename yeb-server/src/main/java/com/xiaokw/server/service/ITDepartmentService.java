package com.xiaokw.server.service;

import com.xiaokw.server.entity.TDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface ITDepartmentService extends IService<TDepartment> {

    List<TDepartment> getAllDepartment();

    Boolean addDep(TDepartment department);

    Integer deleteDep(Integer id);
}
