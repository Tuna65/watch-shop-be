package com.example.watchShop.service;

import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.dto.PackSizeDTO;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.models.Packsize;
import com.example.watchShop.models.Product;
import com.example.watchShop.repository.PackSizeRepository;
import com.example.watchShop.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackSizeService extends AbstractService<Packsize, PackSizeDTO> {

  @Autowired
  private PackSizeRepository packSizeRepository;

  @Autowired
  private ProductRepository productRepository;

  public PackSizeDTO createMain(Product product) {
    Packsize packsize = new Packsize();
    packsize.setId(UUID.randomUUID());
    packsize.setPrice(product.getPrice());
    packsize.setName(product.getDefaultUnitName());
    packsize.setProduct(product);
    return mapToDTO(packSizeRepository.save(packsize));
  }


  public MsgResponse create(PackSizeDTO dto) throws InvalidatedException {
    if (dto.getProductId() == null) {
      throw new InvalidatedException("Vui lòng nhập productId");
    }

    Product product = productRepository.findById(dto.getProductId()).orElse(null);

    if (product == null) {
      throw new EntityNotFoundException("Sản phẩm không tồn tại hoặc đã bị xóa!");
    }
    Packsize packsize = mapToEntity(dto);
    packsize.setProduct(product);
    packsize.setImage(product.getImages().get(0));
    packSizeRepository.save(packsize);
    return new MsgResponse("Success");
  }

  public List<PackSizeDTO> findByProductId(PackSizeDTO dto) {
    List<Packsize> packsizes = packSizeRepository.find(dto);
    return packsizes.stream().map(this::mapToDTO).collect(Collectors.toList());
  }

}
