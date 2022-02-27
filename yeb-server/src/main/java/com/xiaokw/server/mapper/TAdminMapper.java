package com.xiaokw.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaokw.server.entity.TAdmin;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface TAdminMapper extends BaseMapper<TAdmin> {

    List<TAdmin> getAllAdmins(Integer id, String keywords);
}
