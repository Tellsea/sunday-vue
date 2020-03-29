package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.enums.StatusEnums;
import cn.tellsea.sunday.system.entity.LoginLog;
import cn.tellsea.sunday.system.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器
 *
 * @author Tellsea
 * @date 2020-03-29
 */
@Api(tags = "接口")
@RestController
@RequestMapping("/system/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation("表格数据")
    @PostMapping("listByTable")
    public ResponseResult listByTable(LoginLog loginLog) {
        return loginLogService.listByTable(loginLog);
    }

    @ApiOperation("根据ids删除")
    @PostMapping("deleteByIds")
    public ResponseResult deleteByIds(String ids) {
        loginLogService.deleteLoginLogByIds(ids);
        return ResponseResult.success(StatusEnums.DELETE_SUCCESS);
    }
}
