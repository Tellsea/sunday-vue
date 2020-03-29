package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.system.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author Tellsea
 * @date 2020-03-29
 */
public interface LoginLogService extends IService<LoginLog> {

    ResponseResult listByTable(LoginLog loginLog);

    void deleteLoginLogByIds(String ids);
}
