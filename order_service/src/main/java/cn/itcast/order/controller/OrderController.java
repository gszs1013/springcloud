package cn.itcast.order.controller;

import cn.itcast.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private String url = "http://";

    /**
     * @param id 商品的id
     *           通过订单系统调用商品服务，根据id查询商品信息
     *           1.需要配置商品对象
     *           2.需要调用商品服务
     *           在java代码中调用别的接口的技术：
     *           使用java中的urlconnection,  httpclient,okhttp
     *           <p>
     *           spring为我们准备好了一个技术  RestTemplate
     */
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public Product Buy(@PathVariable Long id) {

        //获取Eureka元数据信息  动态拼接url地址
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-product");
        for (ServiceInstance instance :
                serviceInstances) {
            System.out.println(instance);
        }
        ServiceInstance instance = serviceInstances.get(0);


        Product product = null;
        // 如何调用商品服务的接口？
//        product = restTemplate.getForObject("http://127.0.0.1:9001/product/"+id,Product.class);
        product = restTemplate.getForObject(url + instance.getHost() + ":" + instance.getPort() + "/product/" + id, Product.class);
        System.out.println("打印Order中获取的商品信息:" + product.toString());
        return product;

    }
}
