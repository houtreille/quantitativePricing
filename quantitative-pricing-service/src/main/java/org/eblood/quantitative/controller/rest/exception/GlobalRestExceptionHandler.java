package org.eblood.quantitative.controller.rest.exception;

import org.eblood.quantitative.model.valuation.exception.ValuationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalRestExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

  private static final Map<Class, HttpStatus> EXCEPTIONS_MAPPING = new HashMap<>();

  static {
    EXCEPTIONS_MAPPING.put(ValuationException.class, HttpStatus.I_AM_A_TEAPOT);
    EXCEPTIONS_MAPPING.put(MethodArgumentTypeMismatchException.class, HttpStatus.BAD_REQUEST);
    EXCEPTIONS_MAPPING.put(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST);
  }

  private static HttpStatus resolveStatus(Exception exception) {
    return EXCEPTIONS_MAPPING
        .getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<String> handleException(Exception ex, NativeWebRequest request) {
    return getProblemResponseEntity(ex, resolveStatus(ex));
  }

  private ResponseEntity<String> getProblemResponseEntity(Exception exception,
      HttpStatus httpStatus) {
    String problem;
    if (exception instanceof ValuationException) {
      problem = ((ValuationException) exception).getMessage();
    } else {
      LOG.error("Catch exception", exception);
      problem = "XXX";
    }
    return ResponseEntity
        .status(httpStatus)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(problem);
  }

}
