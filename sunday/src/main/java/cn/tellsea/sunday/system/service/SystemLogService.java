package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.SystemLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

/**
 * 系统日志表 Service接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface SystemLogService extends IService<SystemLog> {

    TableData listSystemLogByTable(SystemLog systemLog);

    int deleteSystemLogByIds(String ids);

    @Async
    void saveSystemLog(SystemLog systemLog) throws CrudException;
}
