package styleshare.task.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("CART")
public class Cart {

    private Long id;
    private Long user_id;
    private Long goods_id;
    private Long goods_detail_id;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}