package com.xiaokw.server.service.impl;

import com.xiaokw.server.entity.TEmployee;
import com.xiaokw.server.mapper.TEmployeeMapper;
import com.xiaokw.server.service.ITEmployeeService;
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
public class TEmployeeServiceImpl extends ServiceImpl<TEmployeeMapper, TEmployee> implements ITEmployeeService {

}
