package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.enums.StatusEnums;
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
 * 资源表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "资源表接口")
@RestController
@RequestMapping("/system/resourceInfo")
public class ResourceInfoController {

    @Autowired
    private ResourceInfoService resourceInfoService;

    @ApiOperation("资源树")
    @PostMapping("listByTree")
    public ResponseResult listByTree(ResourceInfo resourceInfo) {
        return ResponseResult.success(resourceInfoService.listByTree(resourceInfo));
    }

    @ApiOperation("新增资源")
    @PostMapping("save")
    public ResponseResult save(ResourceInfo resourceInfo) {
        resourceInfoService.getBaseMapper().insert(resourceInfo);
        return ResponseResult.success(StatusEnums.SAVE_SUCCESS);
    }

    @ApiOperation("更新资源")
    @PostMapping("update")
    public ResponseResult update(ResourceInfo resourceInfo) {
        resourceInfoService.getBaseMapper().updateById(resourceInfo);
        return ResponseResult.success(StatusEnums.UPDATE_SUCCESS);
    }

    @ApiOperation("删除资源")
    @PostMapping("deleteById")
    public ResponseResult deleteById(int id) {
        resourceInfoService.getBaseMapper().deleteById(id);
        return ResponseResult.success(StatusEnums.DELETE_SUCCESS);
    }
}
