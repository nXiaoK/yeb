package com.xiaokw.server.service;

import com.xiaokw.server.entity.TMenu;
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
public interface ITMenuService extends IService<TMenu> {
    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<TMenu> getMenusByAdminId();

    List<TMenu> getMenusWithRole();

    List<TMenu> getAllMenus();
}
