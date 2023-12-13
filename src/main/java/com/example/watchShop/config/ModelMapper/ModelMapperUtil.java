package com.example.watchShop.config.ModelMapper;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.converter.AssignableConverter;

public class ModelMapperUtil {

  public static ModelMapper getDefaultModelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    InheritingConfiguration configuration = (InheritingConfiguration) modelMapper.getConfiguration();

    configuration.setMatchingStrategy(MatchingStrategies.STRICT);
    configuration.setDeepCopyEnabled(true);
    configuration.setFullTypeMatchingRequired(true);

    configuration.converterStore.getConverters().removeIf(x ->
        x.getClass().getName().equals("org.modelmapper.internal.converter.AssignableConverter")
            || x.getClass().getName()
            .equals("org.modelmapper.internal.converter.CollectionConverter"));

    configuration.converterStore.addConverter(new AssignableConverter());
    configuration.converterStore.addConverter(new CollectionConverter());

    return modelMapper;
  }
}