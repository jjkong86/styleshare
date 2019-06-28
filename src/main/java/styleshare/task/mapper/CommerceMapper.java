package styleshare.task.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import styleshare.task.model.Goods;
import styleshare.task.model.GoodsDetail;
import styleshare.task.request.PutAllGoodsToCartRequest;
import styleshare.task.request.PutGoodsToCartRequest;
import styleshare.task.response.GoodsRespose;

@Mapper
public interface CommerceMapper {
	int insertGoods(Goods goods);
	
	int insertGoodsDetail(GoodsDetail gd);

	List<Goods> goodsAll();
	
	List<GoodsDetail> goodsDetailAll();

	GoodsRespose goodsToId();

	int insertGoodsToCart(PutAllGoodsToCartRequest param);

	int putGoodsToCart(PutGoodsToCartRequest param);

}
