package cn.tellsea.sunday.system.service.impl;

import cn.tellsea.sunday.common.entity.SystemConst;
import cn.tellsea.sunday.system.entity.Student;
import cn.tellsea.sunday.system.mapper.StudentMapper;
import cn.tellsea.sunday.system.service.StudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 学生表 Service接口实现类
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Service
@DS(SystemConst.DB_SLAVE)
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
