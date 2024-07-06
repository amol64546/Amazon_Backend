package com.bada.bazaar.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {

  private final ObjectMapper objectMapper;

  @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
  public void handleError(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    log.info("Handling error");

    Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
    if (ObjectUtils.isEmpty(statusCode)) {
      response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
      return;
    }

    String message = (String) request.getAttribute("jakarta.servlet.error.message");
    String error = HttpStatus.valueOf(statusCode).getReasonPhrase();
    String path = (String) request.getAttribute("jakarta.servlet.error.request_uri");
    Throwable exception = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
    log.error("Exception: ", exception);

    if (StringUtils.isEmpty(path)) {
      path = request.getRequestURI();
    }

    if (StringUtils.isEmpty(message) && ObjectUtils.isNotEmpty(exception)) {
      message = exception.getMessage();
    }

    ErrorResponse errorResponse =
      ErrorResponse.builder()
        .timestamp(new Date())
        .status(statusCode)
        .error(error)
        .message(message)
        .path(path)
        .build();

    objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

    response.setStatus(statusCode);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
  }


}
