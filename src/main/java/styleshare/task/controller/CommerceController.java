package styleshare.task.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import styleshare.task.model.Cart;
import styleshare.task.model.CartList;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListResponse;
import styleshare.task.service.CommerceService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
public class CommerceController {

    private CommerceService commerceService;

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public GoodsListResponse goodsAll() {
        return commerceService.goodsAll();
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<CartList> cartAll() {
        return commerceService.cartAll();
    }

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    public ApiCommonResponse putAllGoodsToCart(@RequestBody List<PutGoodsToCartRequest> param) {
        return commerceService.putAllGoodsToCart(param);
    }

    @RequestMapping(value = "/carts", method = RequestMethod.DELETE)
    public ApiCommonResponse deleteToCart(@RequestBody List<PutGoodsToCartRequest> param) {
        return commerceService.deleteToCart(param);
    }

    @RequestMapping(value = "/carts/{goodsId}", method = RequestMethod.POST)
    public ApiCommonResponse putGoodsToCart(@PathVariable("goodsId") int goodsId, @RequestBody PutGoodsToCartRequest param) {
        return commerceService.putGoodsToCart(goodsId, param);
    }

    @RequestMapping(value = "/carts/count", method = RequestMethod.GET)
    public CartResponse cartCount(@Valid Cart cart, BindingResult r) {
        if (r.hasErrors()) {
            log.info(Objects.requireNonNull(r.getFieldError()).getDefaultMessage());
        }
        return commerceService.cartCount();
    }

    @RequestMapping(value = "/payments", method = RequestMethod.PUT)
    public ApiCommonResponse multiPayment(@RequestBody List<PutGoodsToCartRequest> param) {
        return commerceService.multiPayment(param);
    }

    @RequestMapping(value = "/payments/{cartId}", method = RequestMethod.PUT)
    public ApiCommonResponse singlePayment(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
        return commerceService.multiPayment(new ArrayList<>(Collections.singletonList(param)));
    }

    @RequestMapping(value = "/carts/{cartId}", method = RequestMethod.DELETE)
    public ApiCommonResponse singleDeleteToCart(@PathVariable("cartId") int cartId, @RequestBody PutGoodsToCartRequest param) {
        return commerceService.deleteToCart(new ArrayList<>(Collections.singletonList(param)));
    }

    @RequestMapping(value = "/carts/{cartId}", method = RequestMethod.GET)
    public Cart getCartToId(@PathVariable("cartId") int cartId) {
        return commerceService.getCartToId(cartId);
    }
}
