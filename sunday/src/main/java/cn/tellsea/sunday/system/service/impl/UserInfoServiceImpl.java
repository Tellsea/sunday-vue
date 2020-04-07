package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.entity.TableData;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.MapUserRole;
import cn.tellsea.sunday.system.entity.UserInfo;
import cn.tellsea.sunday.system.mapper.UserInfoMapper;
import cn.tellsea.sunday.system.service.MapUserRoleService;
import cn.tellsea.sunday.system.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private MapUserRoleService mapUserRoleService;

    @Override
    public UserInfo getByUserName(String userName) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserName, userName));
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return this.baseMapper.getUserInfoById(id);
    }

    @Override
    public TableData listUserInfoByTable(UserInfo userInfo) {
        int count = this.baseMapper.countUserInfoByTable(userInfo);
        List<UserInfo> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listUserInfoByTable(userInfo);
        }
        return new TableData(count, list);
    }

    @Override
    public int saveUserInfo(UserInfo userInfo) throws CrudException {
        int count = this.baseMapper.insert(userInfo);
        if (count == 0) {
            throw new CrudException(StatusEnums.SAVE_ERROR.getInfo());
        }
        saveMapUserRole(userInfo);
        return count;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) throws CrudException {
        int count = this.baseMapper.updateById(userInfo);
        if (count != 1) {
            throw new CrudException(StatusEnums.UPDATE_ERROR.getInfo());
        }
        mapUserRoleService.remove(new LambdaUpdateWrapper<MapUserRole>().eq(MapUserRole::getUserId, userInfo.getId()));
        saveMapUserRole(userInfo);
        return count;
    }

    private void saveMapUserRole(UserInfo userInfo) {
        String[] split = userInfo.getRoleIds().split(",");
        List<MapUserRole> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(new MapUserRole().setUserId(userInfo.getId()).setRoleId(Integer.valueOf(split[i])));
        }
        mapUserRoleService.saveBatch(list);
    }

    @Override
    public int updateStatus(UserInfo userInfo) throws CrudException {
        int count = this.baseMapper.updateById(userInfo);
        if (count == 0) {
            throw new CrudException(StatusEnums.DELETE_ERROR.getInfo());
        }
        return count;
    }
}
