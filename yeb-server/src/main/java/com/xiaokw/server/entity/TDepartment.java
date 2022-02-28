package com.xiaokw.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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
@TableName("t_department")
@ApiModel(value = "TDepartment对象", description = "")
@NoArgsConstructor
@RequiredArgsConstructor // 表示有参构造
@EqualsAndHashCode(callSuper = false, of = "name")
public class TDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("部门名称")
    @Excel(name = "部门名称", width = 20)
    @NonNull
    private String name;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("路径")
    private String depPath;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("是否上级")
    private Boolean isParent;

    @ApiModelProperty("子部门列表")
    @TableField(exist = false)
    private List<TDepartment> children;

    @ApiModelProperty("返回结果,存储过程使用")
    @TableField(exist = false)
    private Integer result;


}
