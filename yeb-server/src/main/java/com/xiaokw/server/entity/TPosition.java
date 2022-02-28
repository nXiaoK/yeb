package com.xiaokw.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_position")
@ApiModel(value = "TPosition对象", description = "")
@NoArgsConstructor
@RequiredArgsConstructor // 表示有参构造
@EqualsAndHashCode(callSuper = false, of = "name")
public class TPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("职位")
    @Excel(name = "职位", width = 20)
    @NonNull
    private String name;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty("是否启用")
    private Boolean enabled;


}
