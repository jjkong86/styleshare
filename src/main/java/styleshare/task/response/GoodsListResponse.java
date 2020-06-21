package styleshare.task.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import styleshare.task.model.Goods;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GoodsListResponse extends ApiCommonResponse {
    List<Goods> goodsList;

    @Builder
    public GoodsListResponse(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public void addGoodsList(Goods goods) {
        this.goodsList.add(goods);
    }
}
