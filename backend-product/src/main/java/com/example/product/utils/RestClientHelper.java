package com.example.product.utils;

import com.example.product.dto.ErrorDto;
import com.example.product.dto.enumeration.BEMessage;
import com.example.product.exception.BackendException;
import com.example.product.exception.CustomException;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

public class RestClientHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestClientHelper.class);

  public static <T> Response<T> execute(Call<T> request) {
    try {
      Response<T> response = request.execute();
      if (!response.isSuccessful()) {
        return handleError(response);
      }
      return response;
    } catch (IOException e) {
      LOGGER.error("Problem with the server handling the request", e);
      throw new CustomException(BEMessage.UNKNOWN_ERROR, e);
    }
  }

  private static <T> Response<T> handleError(Response<T> response) {
    String contentType = Optional
        .ofNullable(response.errorBody())
        .map(ResponseBody::contentType)
        .map(okhttp3.MediaType::toString)
        .orElse(response.headers().get(HttpHeaders.CONTENT_TYPE));
    if (contentType != null && MediaType.APPLICATION_JSON.includes(MediaType.parseMediaType(contentType))) {
      try {
        ErrorDto errorDto = parseError(response);
        throw new BackendException(errorDto.getCode(), errorDto.getMessage(), response.code());
      } catch (IOException e) {
        LOGGER.error("Could not parse the JSON response as an ErrorDto", e);
      }
    }
    throw new CustomException(BEMessage.OK.UNKNOWN_ERROR);
  }

  private static ErrorDto parseError(Response<?> response)
      throws IOException {
    return Constants.MAPPER.readValue(response.errorBody().byteStream(), ErrorDto.class);
  }
}
