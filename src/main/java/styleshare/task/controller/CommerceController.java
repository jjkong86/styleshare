package styleshare.task.controller;

import java.io.FileNotFoundException;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.request.PutAllGoodsToCartRequest;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.CartResponse;
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
    
    @Transactional
    @RequestMapping(value = "/put/cart", method = RequestMethod.POST)
    public ModelAndView putAllGoodsToCart(@RequestBody PutAllGoodsToCartRequest param, ModelAndView mav) throws FileNotFoundException {
    	CartResponse result = commerceService.putAllGoodsToCart(param);
    	mav.addObject("result", result);
    	return mav;
    }
    
    @Transactional
    @RequestMapping(value = "/put/cart/{goodsId}", method = RequestMethod.POST)
    public CartResponse putGoodsToCart(@RequestBody PutGoodsToCartRequest param) throws FileNotFoundException {
    	CartResponse result = commerceService.putGoodsToCart(param);
    	return result;
    }

}
