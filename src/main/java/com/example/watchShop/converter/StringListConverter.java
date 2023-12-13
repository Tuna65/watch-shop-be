package com.example.watchShop.converter;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> obj) {
    if (obj == null) {
      return null;
    }
    Gson gson = new Gson();
    return gson.toJson(obj);
  }

  @Override
  public List<String> convertToEntityAttribute(String value) {
    if (value == null) {
      return null;
    }
    Gson gson = new Gson();
    String[] strings = gson.fromJson(value, String[].class);
    return Arrays.asList(strings);
  }
}
