package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.consts.SqlPool;
import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.MapRoleResource;
import cn.tellsea.sunday.system.entity.RoleInfo;
import cn.tellsea.sunday.system.mapper.RoleInfoMapper;
import cn.tellsea.sunday.system.service.MapRoleResourceService;
import cn.tellsea.sunday.system.service.RoleInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

    @Autowired
    private MapRoleResourceService mapRoleResourceService;

    @Override
    public List<RoleInfo> getByUserName(String userName) {
        return this.baseMapper.getByUserName(userName);
    }

    @Override
    public TableData listRoleInfoByTable(RoleInfo roleInfo) {
        int count = this.baseMapper.countRoleInfoByTable(roleInfo);
        List<RoleInfo> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listRoleInfoByTable(roleInfo);
        }
        return new TableData(count, list);
    }

    @Override
    public List<RoleInfo> listRoleInfoByAll() {
        return this.baseMapper.selectList(new LambdaQueryWrapper<RoleInfo>()
                .eq(RoleInfo::getStatus, SqlPool.STATUS_NORMAL));
    }

    @Override
    public int saveRole(RoleInfo roleInfo) throws CrudException {
        int count = this.baseMapper.insert(roleInfo);
        if (count == 0) {
            throw new CrudException(StatusEnums.SAVE_ERROR.getInfo());
        }
        saveMapRoleResource(roleInfo);
        return count;
    }

    @Override
    public int updateRole(RoleInfo roleInfo) throws CrudException {
        int count = this.baseMapper.updateById(roleInfo);
        if (count != 1) {
            throw new CrudException(StatusEnums.UPDATE_ERROR.getInfo());
        }
        mapRoleResourceService.remove(new LambdaUpdateWrapper<MapRoleResource>().eq(MapRoleResource::getRoleId, roleInfo.getId()));
        saveMapRoleResource(roleInfo);
        return count;
    }

    private void saveMapRoleResource(RoleInfo roleInfo) {
        String[] split = roleInfo.getResourceIds().split(",");
        List<MapRoleResource> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(new MapRoleResource().setRoleId(roleInfo.getId()).setResourceId(Integer.valueOf(split[i])));
        }
        mapRoleResourceService.saveBatch(list);
    }

    @Override
    public int deleteRoleById(int id) throws CrudException {
        int count = this.baseMapper.updateById(new RoleInfo().setId(id).setStatus(SqlPool.STATUS_DELETE));
        if (count == 0) {
            throw new CrudException(StatusEnums.DELETE_ERROR.getInfo());
        }
        // 修改角色状态为删除即可，中间表保留
        // mapRoleResourceService.remove(new LambdaUpdateWrapper<MapRoleResource>().eq(MapRoleResource::getRoleId, id));
        return count;
    }
}
