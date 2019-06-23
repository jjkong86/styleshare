package styleshare.task.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class GoodsConvert {
	
    private Long id;
    private String name;
    private String provider;
    private Long price;
    private GoodsDetail[] options;
    private Shipping shipping;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}
