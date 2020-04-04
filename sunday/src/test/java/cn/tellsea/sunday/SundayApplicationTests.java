package cn.tellsea.sunday;

import cn.tellsea.sunday.common.entity.ResponseResult;
import cn.tellsea.sunday.common.exception.BaseException;
import cn.tellsea.sunday.common.exception.CrudException;
import cn.tellsea.sunday.system.entity.ResourceInfo;
import cn.tellsea.sunday.system.entity.Student;
import cn.tellsea.sunday.system.mapper.LoginLogMapper;
import cn.tellsea.sunday.system.mapper.ResourceInfoMapper;
import cn.tellsea.sunday.system.mapper.UserInfoMapper;
import cn.tellsea.sunday.system.service.LoginLogService;
import cn.tellsea.sunday.system.service.ResourceInfoService;
import cn.tellsea.sunday.system.service.StudentService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@MapperScan("cn.tellsea.sunday.*.mapper")
@EnableAutoConfiguration
class SundayApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ResourceInfoService resourceInfoService;
    @Autowired
    private ResourceInfoMapper resourceInfoMapper;
    @Autowired
    private LoginLogService loginLogService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
//        UserInfo userInfo = userInfoMapper.selectById(1);
//        System.out.println(JSON.toJSONString(userInfo));

//        Map<String, Object> map = new HashMap<>();
//        map.put("user_name", "admin");
//        List<UserInfo> list = userInfoMapper.selectByMap(map);
//        list.forEach(temp -> System.out.println(JSON.toJSONString(temp)));

//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", "admin")
//                .like("nick_name", "超级");
//        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
//        System.out.println(JSON.toJSONString(userInfo));
//        List<Map<String, Object>> maps = userInfoMapper.selectMaps(queryWrapper);
//        System.out.println(JSON.toJSONString(maps));
//        List<Object> objects = userInfoMapper.selectObjs(queryWrapper);
//        System.out.println(objects.get(0));

//        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("user_name", "admin")
//                .like("nick_name", "超级");
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName("Tellsea");
//        int count = userInfoMapper.update(userInfo, updateWrapper);
//        System.out.println(count);

//        LambdaQueryWrapper<UserInfo> lambda = new QueryWrapper<UserInfo>().lambda();
//        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<UserInfo> lambdaQuery = Wrappers.<UserInfo>lambdaQuery();
//        lambdaQuery.like(UserInfo::getUserName, "te")
//                .lt(UserInfo::getLoginTimes, 0);
//        List<Object> objects = userInfoMapper.selectObjs(lambdaQuery);
//        objects.forEach(System.out::println);

//        Page<UserInfo> page = new Page<>(1, 10);
//        Page<UserInfo> infoPage = userInfoMapper.selectPage(page, null);
//        System.out.println(infoPage);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getOrders());
//        System.out.println(page.getSize());
//        System.out.println(page.getRecords());
//        System.out.println(page.getTotal());

    }

    @Test
    public void druidEncrypt() throws Exception {
        /*String password = "Root123!@#";
        System.out.println("明文: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        String privateKey = keyPair[0];
        String publicKey = keyPair[1];
        password = ConfigTools.encrypt(privateKey, password);
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);
        System.out.println("密文:" + password);
        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密:" + decryptPassword);*/
    }

    @Test
    public void dyDataSource() {
        List<Student> studentList = studentService.list();
        studentList.forEach(System.out::println);
    }

    @Test
    public void stream() {
        List<ResourceInfo> list = resourceInfoService.list();
        List<String> resString = list.stream().map(ResourceInfo::getPerms).distinct().collect(Collectors.toList());
        resString.forEach(System.out::println);
    }
}
