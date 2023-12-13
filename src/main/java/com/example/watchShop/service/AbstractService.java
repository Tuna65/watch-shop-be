package com.example.watchShop.service;

import com.example.watchShop.config.ModelMapper.ModelMapperUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.CastUtils;

public class AbstractService<Entity, DTO> extends BaseService{

  private final Class<Entity> entityClass;

  private final Class<DTO> dtoClass;

  private final ModelMapper modelMapper;

  @SneakyThrows
  public AbstractService() {

    entityClass = CastUtils.cast(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    dtoClass = CastUtils.cast(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    modelMapper = ModelMapperUtil.getDefaultModelMapper();
    configModelMapper(modelMapper);

    getModelMapper().map(getDTO(), getEntity());
    getModelMapper().map(getEntity(), getDTO());

  }

  final protected ModelMapper getModelMapper() {
    return modelMapper;
  }

  protected void configModelMapper(ModelMapper modelMapper) {

  }

  final protected Class<Entity> getEntityClass() {
    return entityClass;
  }

  final protected Class<DTO> getDTOClass() {
    return dtoClass;
  }

  protected Entity getEntity() throws Exception {
    try {
      return entityClass.getConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException
             | NoSuchMethodException e) {
      throw new RuntimeException("Không thể khởi tạo đối tượng " + getEntityName());
    }
  }

  protected DTO getDTO() throws Exception {
    try {
      return dtoClass.getConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException
             | NoSuchMethodException e) {
      throw new RuntimeException("Không thể khởi tạo đối tượng " + getDTOName());
    }
  }

  final public String getEntityName() {
    return entityClass.getSimpleName();
  }

  final public String getDTOName() {
    return entityClass.getSimpleName();
  }

  protected Entity mapToEntity(DTO dto) {
    if (dto == null) {
      return null;
    }

    Entity entity = getModelMapper().map(dto, getEntityClass());

    specificMapToEntity(dto, entity);

    return entity;
  }

  protected void mapToEntity(DTO dto, Entity entity) {
    if (dto == null) {
      return;
    }

    getModelMapper().map(dto, entity);

    specificMapToEntity(dto, entity);

  }

  public DTO mapToDTO(Entity entity) {
    if (entity == null) {
      return null;
    }

    DTO dto = getModelMapper().map(entity, getDTOClass());

    specificMapToDTO(entity, dto);

    return dto;
  }

  protected void mapToDTO(Entity entity, DTO dto) {
    if (entity == null) {
      return;
    }

    getModelMapper().map(entity, dto);

    specificMapToDTO(entity, dto);

  }

  protected void specificMapToDTO(Entity entity, DTO dto) {

  }

  protected void specificMapToEntity(DTO dto, Entity entity) {

  }
}
