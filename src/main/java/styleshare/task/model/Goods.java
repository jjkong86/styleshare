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
    
    @Builder
    public Goods(Goods goods) {
    	this.id = goods.getId();
    	this.name = goods.getName();
    	this.provider = goods.getProvider();
    	shipping.setMethod(goods.getShipping().getMethod());
    	shipping.setPrice(goods.getShipping().getPrice());
    	shipping.setCanBundle(goods.getShipping().getCanBundle());
    	this.regDtm = goods.getRegDtm();
    	this.updDtm = goods.getUpdDtm();
    }
}
