package styleshare.task.response;

import lombok.Builder;
import lombok.Data;
import styleshare.task.constants.Constants;

@Data
public class ExceptionResponse implements Constants {
    int code = CODE_SUCCESS;
    String error;
    
    @Builder
    public ExceptionResponse(int code, String error) {
    	this.code = code;
    	this.error = error;
    };
}
