package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.system.entity.SystemLog;
import cn.tellsea.sunday.system.mapper.SystemLogMapper;
import cn.tellsea.sunday.system.service.SystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统日志表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {

    @Override
    public ResponseResult listByTable(SystemLog systemLog) {
        int count = this.baseMapper.countByTable(systemLog);
        List<SystemLog> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listByTable(systemLog);
        }
        return ResponseResult.table(count, list);
    }

    @Override
    public void deleteSystemLogByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.baseMapper.deleteBatchIds(list);
    }
}
