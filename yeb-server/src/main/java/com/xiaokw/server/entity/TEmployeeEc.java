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
@TableName("t_employee_ec")
@ApiModel(value = "TEmployeeEc对象", description = "")
public class TEmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工编号")
    private Integer eid;

    @ApiModelProperty("奖罚日期")
    private LocalDate ecDate;

    @ApiModelProperty("奖罚原因")
    private String ecReason;

    @ApiModelProperty("奖罚分")
    private Integer ecPoint;

    @ApiModelProperty("奖罚类别，0：奖，1：罚")
    private Integer ecType;

    @ApiModelProperty("备注")
    private String remark;


}
