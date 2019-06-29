package styleshare.task.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("GOODS")
public class Goods {
	
    private long id;
    private String name;
    private String provider;
    private long price;
    private List<GoodsDetail> options = new ArrayList<>();
    private Shipping shipping = new Shipping();
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
    private Cart cart;
}
