package com.xiaokw.server.service.impl;

import com.xiaokw.server.entity.TOplog;
import com.xiaokw.server.mapper.TOplogMapper;
import com.xiaokw.server.service.ITOplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaok
 * @since 2022-02-22
 */
@Service
public class TOplogServiceImpl extends ServiceImpl<TOplogMapper, TOplog> implements ITOplogService {

}
