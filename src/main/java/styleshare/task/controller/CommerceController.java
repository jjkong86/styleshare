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

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CommerceController {

    private final CommerceService commerceService;

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public GoodsListRespose goodsAll() throws FileNotFoundException {
    	return commerceService.goodsAll();
    }
    
    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<CartList> cartAll() {
    	return commerceService.cartAll();
    }
    
    @RequestMapping(value = "/carts/all", method = RequestMethod.POST)
    public ApiCommonResponse putAllGoodsToCart(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.putAllGoodsToCart(param);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/carts/{goodsId}", method = RequestMethod.POST)
    public ApiCommonResponse putGoodsToCart(@PathVariable("goodsId") int goodsId, @RequestBody PutGoodsToCartRequest param) {
    	ApiCommonResponse result = commerceService.putGoodsToCart(goodsId, param);
    	return result;
    }
    
    @RequestMapping(value = "/carts/count", method = RequestMethod.GET)
    public CartResponse cartCount(@Valid Cart cart, BindingResult r) {
    	if (r.hasErrors()) {
    		log.info(r.getFieldError().getDefaultMessage());
    	}
    	CartResponse result = commerceService.cartCount();
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public ApiCommonResponse multiPayment(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.multiPayment(param);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/payments/{cartId}", method = RequestMethod.POST)
    public ApiCommonResponse singlePayment(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
    	List<PutGoodsToCartRequest> list = new ArrayList<>();
    	list.add(param);
    	ApiCommonResponse result = commerceService.multiPayment(list);
    	return result;
    }
    
    @Transactional
    @RequestMapping(value = "/delete/cart", method = RequestMethod.DELETE)
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
