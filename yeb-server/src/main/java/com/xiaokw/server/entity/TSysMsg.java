package com.xiaokw.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("t_sys_msg")
@ApiModel(value = "TSysMsg对象", description = "")
public class TSysMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("消息id")
    private Integer mid;

    @ApiModelProperty("0表示群发消息")
    private Integer type;

    @ApiModelProperty("这条消息是给谁的")
    private Integer adminid;

    @ApiModelProperty("0 未读 1 已读")
    private Integer state;


}
