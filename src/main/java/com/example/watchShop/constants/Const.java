package com.example.watchShop.constants;

public class Const {


  public static final String PHONE_PATTERN = "^(09|03|07|08|05)+([0-9]{8})";
  public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
  public static final String CODE_PATTERN = "^[a-zA-Z0-9]*$";
  public static final String PHONE_CUSTOMER_PATTERN = "^[0-9\\(\\) +-]{8,25}$";
  public static final String[] AUTH_WHITELIST = {
      "/auth/*",
      "/product","/product/*",
      "/comment","/comment/*",
      // -- Swagger UI v2
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      // -- Swagger UI v3 (OpenAPI)
      "/v3/api-docs/**",
      "/swagger-ui/**",
      "/base-resources", "/base-resources/*"
      // other public endpoints of your API may be appended to this array
  };

  public static class FORMAT_DATE_PATTERN {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy  HH:mm:ss";
  }

}
