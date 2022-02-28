package com.xiaokw.server.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rabbitmq.client.AMQP;
import com.xiaokw.server.constant.MailConstants;
import com.xiaokw.server.entity.TMailLog;
import com.xiaokw.server.service.ITMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * rabbitmq配置类
 */
@Configuration
public class RabbitMQConfig {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private ITMailLogService mailLogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        // 消息确认回调，确认消息是否到达broker
        /**
         * data：消息唯一表示
         * ack：确认结果
         * cause：失败原因
         */
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                LOGGER.info("{}==============>消息发送成功", msgId);
                mailLogService.update(new UpdateWrapper<TMailLog>().set("status", 1).eq("msgId", msgId));
            }else{
                LOGGER.error("{}==============>消息发送失败", msgId);
            }
        });

        /**
         * 消息失败回调，比如router不到queue时回调
         * msg:消息主题
         * repCode：响应码
         * repText:相应描述
         * exchange:交换机
         * routingkey：路由主键
         */
//        rabbitTemplate.setReturnsCallback(returnedMessage -> LOGGER.error("{}==============>消息发送queue时失败", returnedMessage.getMessage().getBody()));
        rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingkey)->{
            LOGGER.info("{}==============>消息发送queue时失败", msg.getBody());

        });
        return rabbitTemplate;
    }

    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
