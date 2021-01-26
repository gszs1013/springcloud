package cn.itcast.product.controller;


import cn.itcast.product.entity.Product;
import cn.itcast.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String findById(@RequestBody Product product){
        productService.save(product);
        return "success";
    }
}
