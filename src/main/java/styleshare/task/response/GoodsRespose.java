package styleshare.task.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import styleshare.task.model.Goods;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsRespose extends ApiCommonResponse {
	Goods goods;
}
