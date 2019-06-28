package styleshare.task.request;

import lombok.Data;

@Data
public class PutGoodsToCartRequest {
    private long goods_id;
    private long goods_detail_id;
    private long amount;
    
    PutGoodsToCartRequest(){};
}
