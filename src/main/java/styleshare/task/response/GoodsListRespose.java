package styleshare.task.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import styleshare.task.model.Goods;

@Data
public class GoodsListRespose {
	List<Goods> goods = new ArrayList<>();
}
