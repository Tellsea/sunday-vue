package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.annotation.Log;
import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.enums.CrudEnums;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.ResourceInfo;
import cn.tellsea.sunday.system.service.ResourceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "菜单表接口")
@RestController
@RequestMapping("/system/resourceInfo")
public class ResourceInfoController {

    @Autowired
    private ResourceInfoService resourceInfoService;

    @ApiOperation("菜单树")
    @PostMapping("listByTree")
    public ResponseResult listByTree(ResourceInfo resourceInfo) {
        return ResponseResult.success(resourceInfoService.listResourceInfoByTree(resourceInfo));
    }

    @Log("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping("save")
    public ResponseResult save(ResourceInfo resourceInfo) throws CrudException {
        return ResponseResult.verify(CrudEnums.SAVE, resourceInfoService.saveResourceInfo(resourceInfo));
    }

    @Log("更新菜单")
    @ApiOperation("更新菜单")
    @PostMapping("update")
    public ResponseResult update(ResourceInfo resourceInfo) throws CrudException {
        return ResponseResult.verify(CrudEnums.UPDATE, resourceInfoService.updateResourceInfo(resourceInfo));
    }

    @Log("删除菜单")
    @ApiOperation("删除菜单")
    @PostMapping("deleteById")
    public ResponseResult deleteById(int id) throws CrudException {
        return ResponseResult.verify(CrudEnums.DELETE, resourceInfoService.deleteResourceInfoById(id));
    }
}
