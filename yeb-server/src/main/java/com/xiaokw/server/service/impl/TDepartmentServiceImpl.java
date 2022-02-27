package com.xiaokw.server.service.impl;

import com.xiaokw.server.entity.TDepartment;
import com.xiaokw.server.mapper.TDepartmentMapper;
import com.xiaokw.server.service.ITDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TDepartmentServiceImpl extends ServiceImpl<TDepartmentMapper, TDepartment> implements ITDepartmentService {

    @Autowired
    private TDepartmentMapper departmentMapper;

    @Override
    public List<TDepartment> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }

    @Override
    public Boolean addDep(TDepartment department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
        return department.getResult() > 0;
    }

    @Override
    public Integer deleteDep(Integer id) {
        TDepartment tDepartment = new TDepartment();
        tDepartment.setId(id);
        departmentMapper.deleteDep(tDepartment);
        return tDepartment.getResult();
    }
}
