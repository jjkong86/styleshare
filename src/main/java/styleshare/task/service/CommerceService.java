package styleshare.task.service;

import java.io.FileNotFoundException;
import java.util.List;

import styleshare.task.model.CartList;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.ApiCommonResponse;
import styleshare.task.response.CartResponse;
import styleshare.task.response.GoodsListRespose;

public interface CommerceService {
	GoodsListRespose goodsAll() throws FileNotFoundException;

	GoodsListRespose makeGoodsList(GoodsListRespose goodsList, List<Goods> goods, List<GoodsDetail> goodsDetail);

	GoodsListRespose goodsInsertToJsonFile() throws FileNotFoundException;

	ApiCommonResponse putAllGoodsToCart(List<PutGoodsToCartRequest> param);

	ApiCommonResponse putGoodsToCart(int goodsId, PutGoodsToCartRequest param);

	CartResponse cartCount();

	List<CartList> cartAll();

	ApiCommonResponse multiPayment(List<PutGoodsToCartRequest> param);

	ApiCommonResponse deleteToCart(List<PutGoodsToCartRequest> param);
}
