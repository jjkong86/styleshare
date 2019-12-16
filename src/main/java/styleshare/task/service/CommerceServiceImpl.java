package styleshare.task.service;

import static styleshare.task.constants.Constants.JSON_FILE_PATH;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.common.CommonUtil;
import styleshare.task.exception.ValidCustomException;
import styleshare.task.mapper.CommerceMapper;
import styleshare.task.model.CartList;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListRespose;

@Slf4j
@Service
public class CommerceServiceImpl implements CommerceService {

	private final CommerceMapper commerceMapper;

    public CommerceServiceImpl(CommerceMapper commerceMapper) {
        this.commerceMapper = commerceMapper;
    }

	public GoodsListRespose goodsAll() throws FileNotFoundException {
		GoodsListRespose goodsList = new GoodsListRespose();
		List<Goods> goods = commerceMapper.goodsAll();
		
    	if (goods.size() < 1) { //중복 insert 방지
    		log.info(" ===== goods is empty. insert into db from \"goods.json\" ===== ");
    		return goodsInsertToJsonFile();
    	}
    	
    	List<GoodsDetail> goodsDetail = commerceMapper.goodsDetailAll();
		return makeGoodsList(goodsList, goods, goodsDetail);
	}
	
	public GoodsListRespose makeGoodsList(GoodsListRespose goodsList, 
			List<Goods> goods, List<GoodsDetail> goodsDetail) {
		Map<Long, Goods> map = new HashMap<>();
    	for (Goods g : goods) {
			map.put(g.getId(), g);
			goodsList.getGoods().add(g);
		}
    	
    	for (int i = 0; i < goodsDetail.size(); i++) {
    		long id = goodsDetail.get(i).getGoods_id();
			if (map.containsKey(id)) {
				map.get(id).getOptions().add(goodsDetail.get(i));
			}
		}
    	log.info(goodsList.toString());
		return goodsList;
	}
	
	public GoodsListRespose goodsInsertToJsonFile() throws FileNotFoundException {
    	Gson gson = new Gson();
    	JsonReader reader = new JsonReader(new FileReader(JSON_FILE_PATH));
    	GoodsListRespose data = gson.fromJson(reader, GoodsListRespose.class);
    	List<Goods> list = data.getGoods();
    	for (Goods goods : list) {
    		commerceMapper.insertGoods(goods);
    		List<GoodsDetail> gds = goods.getOptions();
    		for (GoodsDetail gd : gds) {
				gd.setGoods_id(goods.getId());
				commerceMapper.insertGoodsDetail(gd);
			}
		}
    	return data;
	}

	public Goods goodsToId(long id) {
		Goods goods = commerceMapper.goodsToId(id);
		return goods;
	}

	@Transactional
	public ApiCommonResponse putAllGoodsToCart(List<PutGoodsToCartRequest> param) {
		ApiCommonResponse res = new ApiCommonResponse();
		List<Goods> goods = commerceMapper.goodsListToCartExisit(param);
		if (goods.size() > 0) {
			StringJoiner sb = new StringJoiner(", ");
			for (int i = 0; i < goods.size(); i++) {
				sb.add(goods.get(i).getName());
			}
			throw new ValidCustomException("[ "+sb.toString()+"] is exsist to cart.");
		}
		
		if (commerceMapper.putAllGoodsToCart(param) < 1) {
			throw new NoSuchElementException("not match paramter.");
		}
		
		res.setError(CommonUtil.makeString(new PutGoodsToCartRequest(), param) + " insert into cart");
		log.info(res.getError());
		return res;
	}
	
	@Transactional
	public ApiCommonResponse putGoodsToCart(int goodsId, PutGoodsToCartRequest param) {
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
}
