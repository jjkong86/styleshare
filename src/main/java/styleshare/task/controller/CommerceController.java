package styleshare.task.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.model.Cart;
import styleshare.task.model.CartList;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListRespose;
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
    
    @RequestMapping("/cart")
    public List<CartList> cartAll() {
    	return commerceService.cartAll();
    }
    
    @Transactional
    @RequestMapping(value = "/put/cart", method = RequestMethod.POST)
    public ApiCommonResponse putAllGoodsToCart(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.putAllGoodsToCart(param);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/put/cart/{goodsId}", method = RequestMethod.POST)
    public ApiCommonResponse putGoodsToCart(@PathVariable("goodsId") int goodsId, @RequestBody PutGoodsToCartRequest param) {
    	ApiCommonResponse result = commerceService.putGoodsToCart(goodsId, param);
    	return result;
    }
    
    @RequestMapping(value = "/get/cartCount")
    public CartResponse cartCount(@Valid Cart cart, BindingResult r) {
    	if (r.hasErrors()) {
    		log.info(r.getFieldError().getDefaultMessage());
    	}
    	CartResponse result = commerceService.cartCount();
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/put/payment", method = RequestMethod.POST)
    public ApiCommonResponse multiPayment(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.multiPayment(param);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/put/payment/{cartId}", method = RequestMethod.POST)
    public ApiCommonResponse singlePayment(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
    	List<PutGoodsToCartRequest> list = new ArrayList<>();
    	list.add(param);
    	ApiCommonResponse result = commerceService.multiPayment(list);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/delete/cart", method = RequestMethod.POST)
    public ApiCommonResponse deleteToCart(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.deleteToCart(param);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/delete/cart/{cartId}", method = RequestMethod.POST)
    public ApiCommonResponse singleDeleteToCart(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
    	List<PutGoodsToCartRequest> list = new ArrayList<>();
    	list.add(param);
    	ApiCommonResponse result = commerceService.deleteToCart(list);
    	return result;
    }
}
