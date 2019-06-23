package styleshare.task.mapper;

import org.apache.ibatis.annotations.Mapper;

import styleshare.task.model.GoodsConvert;
import styleshare.task.model.Shipping;

@Mapper
public interface CommerceMapper {
	int insertGoods(GoodsConvert goods);
}
