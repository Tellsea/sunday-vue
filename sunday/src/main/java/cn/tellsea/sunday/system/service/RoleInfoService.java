package cn.tellsea.sunday.system.service;

import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.exception.CrudException;
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

    TableData listRoleInfoByTable(RoleInfo roleInfo);

    List<RoleInfo> listRoleInfoByAll();

    int saveRole(RoleInfo roleInfo) throws CrudException;

    int updateRole(RoleInfo roleInfo) throws CrudException;

    int deleteRoleById(int id) throws CrudException;
}
