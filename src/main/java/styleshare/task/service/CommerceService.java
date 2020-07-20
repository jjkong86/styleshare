package styleshare.task.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import styleshare.task.common.CommonUtil;
import styleshare.task.exception.ValidCustomException;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.Cart;
import styleshare.task.model.CartList;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListResponse;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static styleshare.task.constants.Constants.JSON_FILE_PATH;

@Slf4j
@Service
public class CommerceService {

    private final CommerceMapper commerceMapper;

    public CommerceService(CommerceMapper commerceMapper) {
        this.commerceMapper = commerceMapper;
    }

    @PostConstruct
    public void init() throws FileNotFoundException {
        this.goodsInsertToJsonFile();
    }


    public void goodsInsertToJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(JSON_FILE_PATH));
        GoodsListResponse data = gson.fromJson(reader, GoodsListResponse.class);
        List<Goods> list = data.getGoodsList();
        log.info(list.toString());
        for (Goods goods : list) {
            commerceMapper.insertGoods(goods);
            List<GoodsDetail> gds = goods.getOptions();
            for (GoodsDetail gd : gds) {
                gd.setGoods_id(goods.getId());
                commerceMapper.insertGoodsDetail(gd);
            }
        }
    }

    public GoodsListResponse goodsAll() {
        List<Goods> goods = commerceMapper.goodsAll();
        List<GoodsDetail> goodsDetail = commerceMapper.goodsDetailAll();
        return makeGoodsList(goods, goodsDetail);
    }

    public GoodsListResponse makeGoodsList(List<Goods> goods, List<GoodsDetail> goodsDetail) {
        Map<Long, List<GoodsDetail>> detailMap = goodsDetail.stream().collect(Collectors.groupingBy(GoodsDetail::getGoods_id));
        goods.forEach(elem -> elem.getOptions().addAll(detailMap.getOrDefault(elem.getId(), new ArrayList<>())));
        log.info(goods.toString());

        return GoodsListResponse.builder().goodsList(goods).build();
    }

    @Transactional
    public ApiCommonResponse putAllGoodsToCart(List<PutGoodsToCartRequest> param) {
        ApiCommonResponse res = new ApiCommonResponse();
        List<Goods> goods = commerceMapper.goodsListToCartExisit(param);
        if (goods.size() > 0) {
            StringJoiner sb = new StringJoiner(", ");
            for (Goods good : goods) {
                sb.add(good.getName());
            }
            throw new ValidCustomException("[ " + sb.toString() + "] is exsist to cart.");
        }

        if (commerceMapper.putAllGoodsToCart(param) < 1) {
            throw new NoSuchElementException("not match paramter.");
        }

        res.setError(CommonUtil.makeString(new PutGoodsToCartRequest(), param) + " insert into cart");
        log.info(res.getError());
        return res;
    }

    @Transactional
    public ApiCommonResponse putGoodsToCart(PutGoodsToCartRequest param) {
        ApiCommonResponse res = new CartResponse();
        int cart = commerceMapper.goodsToCartExisit(param);
        if (cart > 0) throw new ValidCustomException("goods exsist to cart.");

        if (commerceMapper.putGoodsToCart(param) < 1) {
            throw new ValidCustomException("not match paramter.");
        }
        res.setError(param.getName() + " insert into cart");
        log.info(res.getError());
        return res;
    }

    public CartResponse cartCount() {
        CartResponse res = new CartResponse();
        res.setCount(commerceMapper.cartCount());
        return res;
    }

    public List<CartList> cartAll() {
        List<CartList> result = commerceMapper.goodsDetailToCart();
        log.info(result.toString());
        return result;
    }

    @Transactional
    public ApiCommonResponse multiPayment(List<PutGoodsToCartRequest> param) {
        ApiCommonResponse result = new ApiCommonResponse();
        StringJoiner sj = new StringJoiner(", ");
        for (PutGoodsToCartRequest p : param) {
            sj.add(p.getName());
            int stockUpdate = commerceMapper.stockUpdate(p);
            if (stockUpdate < 1) {
                throw new ValidCustomException(p.getGoods_detail_id() + " is update fail to stock.");
            }
            log.info(p.getGoods_detail_id() + " is update to stock.");
        }

        int delete = commerceMapper.multiPayment(param);
        if (delete < 1) {
            throw new ValidCustomException("multiPayment fail.");
        }

        result.setError(sj.toString() + " is payment.");
        log.info(result.getError());
        return result;
    }

    @Transactional
    public ApiCommonResponse deleteToCart(List<PutGoodsToCartRequest> param) {
        ApiCommonResponse result = new ApiCommonResponse();
        int delete = commerceMapper.multiPayment(param);
        if (delete < 1) {
            throw new ValidCustomException("multiPayment fail.");
        }

        result.setError(CommonUtil.makeString(new PutGoodsToCartRequest(), param) + " is delete.");
        log.info(result.getError());
        return result;
    }

    public Cart getCartToId(int cartId) {
        Cart result = commerceMapper.getCartToId(cartId);
        log.info(result.toString());
        return result;
    }

    @Cacheable(cacheNames = "goods", key = "#goodsId")
    public Goods goodsById(long goodsId) {
        List<Goods> goods = commerceMapper.goodsAll();
        return goods.stream().filter(elem -> elem.getId() == goodsId).findFirst()
                .orElseThrow(() -> new ValidCustomException("goods Id : " + goodsId + " is not found."));
    }
}
