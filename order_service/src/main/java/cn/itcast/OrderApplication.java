package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("cn.itcast.order.entity")
public class OrderApplication {
    /**
     *
     * @param args
     *
     *
     * 使用spring自带的RestTemplate技术发送http请求商品服务的接口
     *      1.创建一个RestTemplate对象交给容器管理
     *      2.使用的时候调用其方法完成操作
     *
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
