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
@TableName("t_nation")
@RequiredArgsConstructor // 表示有参构造
@EqualsAndHashCode(callSuper = false, of = "name") // 表示重写equals和hashcode 并且以name属性判断对象是否相等
@NoArgsConstructor
@ApiModel(value = "TNation对象", description = "")
public class TNation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("民族")
    @Excel(name = "民族", width = 8)
    @NonNull
    private String name;


}
