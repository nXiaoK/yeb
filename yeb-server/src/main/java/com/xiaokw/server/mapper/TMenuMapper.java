package com.xiaokw.server.mapper;

import com.xiaokw.server.entity.TMenu;
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
public interface TMenuMapper extends BaseMapper<TMenu> {

    List<TMenu> getMenusByAdminId(Integer id);

    List<TMenu> getMenusWithRole();

    List<TMenu> getAllMenus();
}
