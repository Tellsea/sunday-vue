package cn.tellsea.sunday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Sunday 启动类
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@EnableAsync
@SpringBootApplication
public class SundayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SundayApplication.class, args);
    }

}
