package com.xiaokw.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaokw.server.entity.TAdminRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface TAdminRoleMapper extends BaseMapper<TAdminRole> {

    Integer addAdminRole(Integer adminId, Integer[] rids);

}
