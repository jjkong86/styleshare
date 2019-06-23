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
@Alias("GOODS")
public class Goods {
    private Long id;
    private String name;
    private String provider;
    private Long price;
    private String shipping_method;
    private Long shipping_price;
    private Boolean shipping_canBundle;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}
