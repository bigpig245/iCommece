package com.example.product.config;

import com.example.product.rest.CustomerRest;
import com.example.product.rest.ProductRest;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import com.example.product.utils.Constants;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
public class RetrofitConfiguration {

  @Value("${service.url.product}")
  private String product;
  @Value("${service.url.user}")
  private String user;
  @Value("${rest.client.timeout:60}")
  private int timeout;

  @Autowired
  private OkHttpClient okHttpClient;

  @Bean
  public ProductRest productRest() {
    return createClient(this.product, ProductRest.class,
        this::internalHttpClient);
  }

  @Bean
  public CustomerRest customerRest() {
    return createClient(this.user, CustomerRest.class,
        this::internalHttpClient);
  }

  @Bean
  public OkHttpClient internalHttpClient() {
    return createOkHttpClient();
  }

  private <T> T createClient(String baseUrl, Class<T> clazz, Supplier<OkHttpClient> clientSupplier) {
    return new Retrofit.Builder()
        .callFactory(okHttpClient)
        .baseUrl(baseUrl)
        .client(clientSupplier.get())
        .addConverterFactory(JacksonConverterFactory.create(Constants.MAPPER))
        .build()
        .create(clazz);
  }

  private OkHttpClient createOkHttpClient() {
    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

    return okHttpClientBuilder
        .readTimeout(this.timeout, TimeUnit.SECONDS)
        .build();
  }

}
