package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.system.entity.SystemLog;
import cn.tellsea.sunday.system.service.SystemLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "系统日志表接口")
@RestController
@RequestMapping("/system/systemLog")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @ApiOperation("表格数据")
    @PostMapping("listByTable")
    public ResponseResult listByTable(SystemLog systemLog) {
        return systemLogService.listByTable(systemLog);
    }

    @ApiOperation("根据ids删除")
    @PostMapping("deleteByIds")
    public ResponseResult deleteByIds(String ids) {
        systemLogService.deleteSystemLogByIds(ids);
        return ResponseResult.success(StatusEnums.DELETE_SUCCESS);
    }
}
