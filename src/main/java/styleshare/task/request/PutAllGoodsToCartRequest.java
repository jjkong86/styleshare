package styleshare.task.request;

import java.util.List;

import lombok.Data;

@Data
public class PutAllGoodsToCartRequest {
    private List<PutGoodsToCartRequest> goodsList;
}
