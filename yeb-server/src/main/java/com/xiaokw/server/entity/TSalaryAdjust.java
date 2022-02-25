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
@TableName("t_salary_adjust")
@ApiModel(value = "TSalaryAdjust对象", description = "")
public class TSalaryAdjust implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工ID")
    private Integer eid;

    @ApiModelProperty("调薪日期")
    private LocalDate asDate;

    @ApiModelProperty("调前薪资")
    private Integer beforeSalary;

    @ApiModelProperty("调后薪资")
    private Integer afterSalary;

    @ApiModelProperty("调薪原因")
    private String reason;

    @ApiModelProperty("备注")
    private String remark;


}
