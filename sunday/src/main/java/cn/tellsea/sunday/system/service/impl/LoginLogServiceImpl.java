package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.LoginLog;
import cn.tellsea.sunday.system.mapper.LoginLogMapper;
import cn.tellsea.sunday.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-29
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public TableData listLoginLogByTable(LoginLog loginLog) {
        int count = this.baseMapper.countByTable(loginLog);
        List<LoginLog> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listByTable(loginLog);
        }
        return new TableData(count, list);
    }

    @Override
    public int deleteLoginLogByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(StringPool.COMMA));
        return this.baseMapper.deleteBatchIds(list);
    }
}
