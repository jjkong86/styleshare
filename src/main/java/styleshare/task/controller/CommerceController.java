package styleshare.task.controller;

import java.io.FileNotFoundException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.response.GoodsListRespose;
import styleshare.task.response.GoodsRespose;
import styleshare.task.service.CommerceService;

@Slf4j
@RestController
public class CommerceController {

    private final CommerceService commerceService;

    public CommerceController(CommerceService commerceService) {
        this.commerceService = commerceService;
    }
    
    @RequestMapping("/goods")
    public GoodsListRespose goodsAll() throws FileNotFoundException {
    	return commerceService.goodsAll();
    }
    
    @RequestMapping("/goods/{id}")
    public GoodsRespose goodsToId(@PathVariable("id") int id) throws FileNotFoundException {
    	return commerceService.goodsToId(id);
    }

}
