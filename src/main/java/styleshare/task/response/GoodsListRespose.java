package styleshare.task.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import styleshare.task.model.Goods;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsListRespose extends ApiCommonResponse {
	List<Goods> goods;
	
	public GoodsListRespose() {
		this.goods = new ArrayList<>();
	}
	public GoodsListRespose(List<Goods> list) {
		this.goods = list;
	}
}
