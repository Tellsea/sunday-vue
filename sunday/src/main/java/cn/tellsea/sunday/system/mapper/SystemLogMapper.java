package cn.tellsea.sunday.system.mapper;

import cn.tellsea.sunday.system.entity.SystemLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志表 Mapper接口
 *
 * @author Tellsea
 * @date 2020-03-04
 */
public interface SystemLogMapper extends BaseMapper<SystemLog> {

    int countByTable(@Param("entity")SystemLog systemLog);

    List<SystemLog> listByTable(@Param("entity")SystemLog systemLog);

}
