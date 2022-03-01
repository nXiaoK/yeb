package com.xiaokw.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_salary")
@ApiModel(value = "TSalary对象", description = "")
public class TSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("基本工资")
    @Excel(name = "基本工资", width = 10,suffix = "元")
    private Integer basicSalary;

    @ApiModelProperty("奖金")
    private Integer bonus;

    @ApiModelProperty("午餐补助")
    private Integer lunchSalary;

    @ApiModelProperty("交通补助")
    private Integer trafficSalary;

    @ApiModelProperty("应发工资")
    private Integer allSalary;

    @ApiModelProperty("养老金基数")
    private Integer pensionBase;

    @ApiModelProperty("养老金比率")
    private Float pensionPer;

    @ApiModelProperty("启用时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty("医疗基数")
    private Integer medicalBase;

    @ApiModelProperty("医疗保险比率")
    private Float medicalPer;

    @ApiModelProperty("公积金基数")
    private Integer accumulationFundBase;

    @ApiModelProperty("公积金比率")
    private Float accumulationFundPer;

    @ApiModelProperty("名称")
    private String name;


}
