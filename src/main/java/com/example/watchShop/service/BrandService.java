package com.example.watchShop.service;

import com.example.watchShop.dto.BrandDTO;
import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.enums.Status;
import com.example.watchShop.models.Brand;
import com.example.watchShop.repository.BrandRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends AbstractService<Brand, BrandDTO> {

  @Autowired
  private BrandRepository brandRepository;

  public BrandDTO create(BrandDTO dto) {
    dto.setStatus(Status.ACTIVE);
    return mapToDTO(brandRepository.save(mapToEntity(dto)));
  }

  public MsgResponse delete(UUID id) {
    Brand brand = checkNotFound(id);
    brand.setStatus(Status.DELETED);
    brandRepository.save(brand);
    return new MsgResponse("Xóa nhãn hàng thành công!");
  }

  public List<BrandDTO> getAll() {
    return brandRepository.findAllByStatus().stream().map(this::mapToDTO).collect(
        Collectors.toList());
  }

  //validate
  public Brand checkNotFound(UUID id) {
    Brand brand = brandRepository.findById(id).orElse(null);
    if (brand == null) {
      throw new EntityNotFoundException("Có lỗi xảy ra vui lòng thử lại!");
    }
    return brand;
  }
}
