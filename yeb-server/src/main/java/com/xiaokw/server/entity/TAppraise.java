package com.xiaokw.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("t_appraise")
@ApiModel(value = "TAppraise对象", description = "")
public class TAppraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工id")
    private Integer eid;

    @ApiModelProperty("考评日期")
    private LocalDate appDate;

    @ApiModelProperty("考评结果")
    private String appResult;

    @ApiModelProperty("考评内容")
    private String appContent;

    @ApiModelProperty("备注")
    private String remark;


}
