package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.system.entity.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表 Service接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface RoleInfoService extends IService<RoleInfo> {

    List<RoleInfo> getByUserName(String userName);

    ResponseResult listByTable(RoleInfo roleInfo);

    List<RoleInfo> listRoleInfoByAll();

    void saveRole(RoleInfo roleInfo);

    void updateRole(RoleInfo roleInfo);

    void deleteRole(int id);
}
