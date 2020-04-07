package cn.tellsea.sunday.system.controller;

import cn.tellsea.sunday.system.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户角色关联表接口")
@RestController
@RequestMapping("/system/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
}
