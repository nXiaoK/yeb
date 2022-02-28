package com.xiaokw.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

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
@TableName("t_politics_status")
@NoArgsConstructor
@ApiModel(value = "TPoliticsStatus对象", description = "")
@RequiredArgsConstructor // 表示有参构造
@EqualsAndHashCode(callSuper = false, of = "name")
public class TPoliticsStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("政治面貌")
    @Excel(name = "政治面貌", width = 20)
    @NonNull
    private String name;


}
