package styleshare.task.response;

import lombok.Builder;
import lombok.Data;
import styleshare.task.constants.Constants;

@Data
public class ApiCommonResponse implements Constants {
    int code = CODE_SUCCESS;
    String error;
    
    public ApiCommonResponse() {
    };
}
