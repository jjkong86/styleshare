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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import styleshare.task.model.Cart;
import styleshare.task.model.CartList;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListRespose;
import styleshare.task.service.CommerceService;
import styleshare.task.service.CommerceServiceImpl;

@Slf4j
@RestController
@AllArgsConstructor
public class CommerceController {

    private CommerceServiceImpl commerceService;

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public GoodsListRespose goodsAll() throws FileNotFoundException {
    	return commerceService.goodsAll();
    }
    
    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<CartList> cartAll() {
    	return commerceService.cartAll();
    }
    
    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    public ApiCommonResponse putAllGoodsToCart(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.putAllGoodsToCart(param);
    	return result;
    }
    
    @RequestMapping(value = "/carts", method = RequestMethod.DELETE)
    public ApiCommonResponse deleteToCart(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.deleteToCart(param);
    	return result;
    }
    
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
    
    @RequestMapping(value = "/payments", method = RequestMethod.PUT)
    public ApiCommonResponse multiPayment(@RequestBody List<PutGoodsToCartRequest> param) {
    	ApiCommonResponse result = commerceService.multiPayment(param);
    	return result;
    }
    
    @RequestMapping(value = "/payments/{cartId}", method = RequestMethod.PUT)
    public ApiCommonResponse singlePayment(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
    	List<PutGoodsToCartRequest> list = new ArrayList<>();
    	list.add(param);
    	ApiCommonResponse result = commerceService.multiPayment(list);
    	return result;
    }
    
    @RequestMapping(value = "/carts/{cartId}", method = RequestMethod.DELETE)
    public ApiCommonResponse singleDeleteToCart(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
    	List<PutGoodsToCartRequest> list = new ArrayList<>();
    	list.add(param);
    	ApiCommonResponse result = commerceService.deleteToCart(list);
		return result;
	}
}
