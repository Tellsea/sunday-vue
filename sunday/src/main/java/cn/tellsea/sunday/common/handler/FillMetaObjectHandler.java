package cn.tellsea.sunday.common.handler;

import cn.tellsea.sunday.common.consts.SqlPool;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 自动填充
 *
 * @author Tellsea
 * @date 2020/3/3
 */
@Slf4j
@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("FillMetaObjectHandler 开始自动插入新增 ...");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date(System.currentTimeMillis()));
        this.strictInsertFill(metaObject, "status", Integer.class, SqlPool.STATUS_NORMAL);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            log.info("FillMetaObjectHandler 开始自动插入更新 ...");
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date(System.currentTimeMillis()));
        }
    }
}
