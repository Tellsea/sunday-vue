package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.entity.ResponseResult;
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
    public ResponseResult listByTable(UserInfo userInfo) {
        int count = this.baseMapper.countByTable(userInfo);
        List<UserInfo> list = new ArrayList<>();
        if (count > 0) {
            list = this.baseMapper.listByTable(userInfo);
        }
        return ResponseResult.table(count, list);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        this.baseMapper.insert(userInfo);
        saveMapUserRole(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        this.updateById(userInfo);
        mapUserRoleService.remove(new LambdaUpdateWrapper<MapUserRole>().eq(MapUserRole::getUserId, userInfo.getId()));
        saveMapUserRole(userInfo);
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
    public void updateStatus(UserInfo userInfo) {
        this.baseMapper.updateById(userInfo);
    }
}
