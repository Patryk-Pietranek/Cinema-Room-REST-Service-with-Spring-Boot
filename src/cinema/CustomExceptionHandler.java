package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<CustomExceptionResponse> handleApiException(CustomException ex) {
        return new ResponseEntity<>(new CustomExceptionResponse(ex.getMessage()), ex.getStatus());
    }

}
