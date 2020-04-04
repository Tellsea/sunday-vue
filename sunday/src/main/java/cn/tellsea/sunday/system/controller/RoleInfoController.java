package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.annotation.Log;
import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.enums.CrudEnums;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.RoleInfo;
import cn.tellsea.sunday.system.service.RoleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "角色表接口")
@RestController
@RequestMapping("/system/roleInfo")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    @ApiOperation("数据表格")
    @PostMapping("listByTable")
    public ResponseResult listByTable(RoleInfo roleInfo) {
        return ResponseResult.table(roleInfoService.listRoleInfoByTable(roleInfo));
    }

    @ApiOperation("所有角色")
    @GetMapping("listByAll")
    public ResponseResult listByAll() {
        return ResponseResult.success(roleInfoService.listRoleInfoByAll());
    }

    @Log("新增角色")
    @ApiOperation("新增角色")
    @PostMapping("save")
    public ResponseResult save(RoleInfo roleInfo) throws CrudException {
        return ResponseResult.verify(CrudEnums.SAVE, roleInfoService.saveRole(roleInfo));
    }

    @Log("更新角色")
    @ApiOperation("更新角色")
    @PostMapping("update")
    public ResponseResult update(RoleInfo roleInfo) throws CrudException {
        return ResponseResult.verify(CrudEnums.UPDATE, roleInfoService.updateRole(roleInfo));
    }

    @Log("删除角色")
    @ApiOperation("删除角色")
    @PostMapping("deleteById")
    public ResponseResult deleteById(int id) throws CrudException {
        return ResponseResult.verify(CrudEnums.DELETE, roleInfoService.deleteRoleById(id));
    }
}
