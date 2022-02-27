package com.xiaokw.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaokw.server.entity.TMenuRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
public interface ITMenuRoleService extends IService<TMenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    Boolean updateMenuRole(Integer rid, Integer[] mids);
}
