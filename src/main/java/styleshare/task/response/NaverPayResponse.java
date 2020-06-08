package styleshare.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class NaverPayResponse extends ApiCommonResponse {
    String description;
}
