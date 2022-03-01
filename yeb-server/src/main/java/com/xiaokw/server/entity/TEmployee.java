package com.xiaokw.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

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
@TableName("t_employee")
@ApiModel(value = "TEmployee对象", description = "")
public class TEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("员工编号")
//    @Excel(name = "员工编号", width = 10)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工姓名")
    @Excel(name = "员工姓名", width = 10)
    private String name;

    @ApiModelProperty("性别")
    @Excel(name = "性别", width = 5)
    private String gender;

    @ApiModelProperty("出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "出生日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty("身份证号")
    @Excel(name = "身份证号", width = 30)
    private String idCard;

    @ApiModelProperty("婚姻状况")
    @Excel(name = "婚姻状况", width = 5)
    private String wedlock;

    @ApiModelProperty("民族")
    @Excel(name = "民族", width = 5)
    private Integer nationId;

    @ApiModelProperty("籍贯")
    @Excel(name = "籍贯", width = 10)
    private String nativePlace;

    @ApiModelProperty("政治面貌")
    @Excel(name = "政治面貌", width = 10)
    private Integer politicId;

    @ApiModelProperty("邮箱")
    @Excel(name = "邮箱", width = 30)
    private String email;

    @ApiModelProperty("电话号码")
    @Excel(name = "电话号码", width = 20)
    private String phone;

    @ApiModelProperty("联系地址")
    @Excel(name = "联系地址", width = 40)
    private String address;

    @ApiModelProperty("所属部门")
    private Integer departmentId;

    @ApiModelProperty("职称ID")
    private Integer jobLevelId;

    @ApiModelProperty("职位ID")
    private Integer posId;

    @ApiModelProperty("聘用形式")
    @Excel(name = "聘用形式", width = 10)
    private String engageForm;

    @ApiModelProperty("最高学历")
    @Excel(name = "最高学历", width = 10)
    private String tiptopDegree;

    @ApiModelProperty("所属专业")
    @Excel(name = "所属专业", width = 25)
    private String specialty;

    @ApiModelProperty("毕业院校")
    @Excel(name = "毕业院校", width = 20)
    private String school;

    @ApiModelProperty("入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "入职日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate beginDate;

    @ApiModelProperty("在职状态")
    @Excel(name = "在职状态", width = 10)
    private String workState;

    @ApiModelProperty("工号")
    @Excel(name = "工号", width = 10)
    private String workID;

    @ApiModelProperty("合同期限")
    @Excel(name = "合同期限", width = 10,suffix = "年")
    private Double contractTerm;

    @ApiModelProperty("转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "转正日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate conversionTime;

    @ApiModelProperty("离职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "离职日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate notWorkDate;

    @ApiModelProperty("合同起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同起始日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate beginContract;

    @ApiModelProperty("合同终止日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同终止日期", width = 20,format = "yyyy-MM-dd")
    private LocalDate endContract;

    @ApiModelProperty("工龄")
    @Excel(name = "工龄", width = 5,suffix = "年")
    private Integer workAge;

    @ApiModelProperty("工资账套ID")
    private Integer salaryId;

    @ApiModelProperty("民族")
    @TableField(exist = false)
    @ExcelEntity(name = "民族")
    private TNation nation;

    @ApiModelProperty("政治面貌")
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private TPoliticsStatus politicsStatus;

    @ApiModelProperty("部门")
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private TDepartment department;

    @ApiModelProperty("职称")
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private TJoblevel joblevel;

    @ApiModelProperty("职位")
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private TPosition position;


    @ApiModelProperty("工资账套")
    @TableField(exist = false)
    @ExcelEntity(name = "工资账套")
    private TSalary salary;




}
