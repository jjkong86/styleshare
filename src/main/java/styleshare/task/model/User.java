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
@Alias("USER")
public class User {
    private long id;
    private LocalDateTime regDtm;
    private LocalDateTime updDtm;
}
