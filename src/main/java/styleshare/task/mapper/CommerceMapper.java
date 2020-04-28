package styleshare.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Component;
import styleshare.task.model.Cart;
import styleshare.task.model.CartList;
import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.model.User;
import styleshare.task.request.PutGoodsToCartRequest;

@Component
public interface CommerceMapper {
	int insertGoods(Goods goods);
	
	int insertGoodsDetail(GoodsDetail gd);

	List<Goods> goodsAll();
	
	List<GoodsDetail> goodsDetailAll();

	Goods goodsToId(long id);

	int putAllGoodsToCart(List<PutGoodsToCartRequest> param);

	int putGoodsToCart(PutGoodsToCartRequest param);

	int cartCount();

	int goodsToCartExisit(PutGoodsToCartRequest param);

	List<Goods> goodsListToCartExisit(List<PutGoodsToCartRequest> param);
	
	List<Goods> goodsToCart();

	List<CartList> goodsDetailToCart();

	int stockUpdate(PutGoodsToCartRequest param);

	int multiPayment(List<PutGoodsToCartRequest> param);
	
	User getUserInfo(User user);

	List<String> getAuthorities(User user);

	Cart getCartToId(int cartId);

}
