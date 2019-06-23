package styleshare.task.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("GOODS_DETAIL")
public class GoodsDetail {

    private Long id;
    private String color;
    private String size;
    private Long stock;
    private Long goods_id;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;

//    @Builder
//    private GoodsDetail(Long id, Long refId) {
//        this.id = id;
//        this.refId = refId;
//    }
}
