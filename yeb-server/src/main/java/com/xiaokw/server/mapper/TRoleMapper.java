package com.xiaokw.server.mapper;

import com.xiaokw.server.entity.TRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface TRoleMapper extends BaseMapper<TRole> {
    List<TRole> getRoles(Integer adminId);
}
