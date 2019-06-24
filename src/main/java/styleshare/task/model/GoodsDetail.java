package styleshare.task.model;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("GOODS_DETAIL")
public class GoodsDetail {

    private long id;
    private String color;
    private String size;
    private long stock;
    private long goods_id;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;

}
