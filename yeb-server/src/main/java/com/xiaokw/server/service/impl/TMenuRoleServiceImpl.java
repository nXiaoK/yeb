package com.xiaokw.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaokw.server.entity.TMenuRole;
import com.xiaokw.server.mapper.TMenuRoleMapper;
import com.xiaokw.server.service.ITMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TMenuRoleServiceImpl extends ServiceImpl<TMenuRoleMapper, TMenuRole> implements ITMenuRoleService {

    @Autowired
    private TMenuRoleMapper menuRoleMapper;

    @Override
    public Boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<TMenuRole>().eq("rid", rid));
        return (null == mids || 0 == mids.length) || menuRoleMapper.insertRecord(rid,mids) > 0;
    }
}
