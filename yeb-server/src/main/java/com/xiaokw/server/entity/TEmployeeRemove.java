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
@TableName("t_employee_remove")
@ApiModel(value = "TEmployeeRemove对象", description = "")
public class TEmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工id")
    private Integer eid;

    @ApiModelProperty("调动后部门")
    private Integer afterDepId;

    @ApiModelProperty("调动后职位")
    private Integer afterJobId;

    @ApiModelProperty("调动日期")
    private LocalDate removeDate;

    @ApiModelProperty("调动原因")
    private String reason;

    @ApiModelProperty("备注")
    private String remark;


}
