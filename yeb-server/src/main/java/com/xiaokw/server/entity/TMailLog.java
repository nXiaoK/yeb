package com.xiaokw.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("t_mail_log")
@ApiModel(value = "TMailLog对象", description = "")
public class TMailLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("消息id")
    private String msgId;

    @ApiModelProperty("接收员工id")
    private Integer eid;

    @ApiModelProperty("状态（0:消息投递中 1:投递成功 2:投递失败）")
    private Integer status;

    @ApiModelProperty("路由键")
    private String routeKey;

    @ApiModelProperty("交换机")
    private String exchange;

    @ApiModelProperty("重试次数")
    private Integer count;

    @ApiModelProperty("重试时间")
    private LocalDateTime tryTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


}
