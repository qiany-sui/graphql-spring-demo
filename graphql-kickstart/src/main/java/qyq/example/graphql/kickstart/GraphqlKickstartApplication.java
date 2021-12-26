package qyq.example.graphql.kickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 没加什么东西,就是默认的配置
 */
@SpringBootApplication
@MapperScan("qyq.example.graphql.kickstart.mapper")
public class GraphqlKickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlKickstartApplication.class, args);
    }

}
