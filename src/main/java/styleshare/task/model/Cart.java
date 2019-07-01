package styleshare.task.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Alias("CART")
public class Cart {

	@Min(value=1, message="id is bigger than 0.")
    private long id;
    private long goods_id;
    private long goods_detail_id;
    private long amount;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}
