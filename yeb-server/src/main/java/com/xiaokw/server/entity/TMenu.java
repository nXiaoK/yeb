package com.xiaokw.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Getter
@Setter
@TableName("t_menu")
@ApiModel(value = "TMenu对象", description = "")
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("path")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("图标")
    private String iconCls;

    @ApiModelProperty("是否保持激活")
    private Boolean keepAlive;

    @ApiModelProperty("是否要求权限")
    private Boolean requireAuth;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("子菜单")
    @TableField(exist = false) // 表示表字段里没有此字段
    private List<TMenu> children;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<TRole> roles;
}
