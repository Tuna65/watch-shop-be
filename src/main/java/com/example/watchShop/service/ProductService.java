package com.example.watchShop.service;

import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.dto.ProductDTO;
import com.example.watchShop.enums.Status;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.models.Product;
import com.example.watchShop.repository.ProductRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService extends AbstractService<Product, ProductDTO> {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private PackSizeService packSizeService;

  public ProductDTO create(ProductDTO dto) {
    Product product = mapToEntity(dto);
    product.setStatus(Status.ACTIVE);
    Product newProduct = productRepository.save(product);
    packSizeService.createMain(newProduct);
    return mapToDTO(newProduct);
  }

  @Transactional
  public ProductDTO update(ProductDTO dto, UUID id) throws InvalidatedException {
    Product product = productRepository.findByIdAndStatus(id, Status.ACTIVE);
    if (product == null) {
      throw new InvalidatedException("Sản phẩm không tồn tại hoặc đã bị xóa!");
    }
    mapToEntity(dto, product);
    return mapToDTO(productRepository.save(product));
  }

  public ProductDTO detail(UUID id) throws InvalidatedException {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      throw new InvalidatedException("Sản phẩm không tồn tại hoặc đã bị xóa!");
    }
    return mapToDTO(product);
  }

  public Page<ProductDTO> bestSaler(
      Pageable pageable) {
    return productRepository.bestSaler(pageable).map(this::mapToDTO);
  }

  public Page<ProductDTO> search(ProductDTO dto, Pageable pageable) {
    return productRepository.search(dto, pageable).map(this::mapToDTO);
  }

  public MsgResponse delete(UUID id) throws InvalidatedException {
    Product product = productRepository.findByIdAndStatus(id, Status.ACTIVE);
    if (product == null) {
      throw new InvalidatedException("Sản phẩm không tồn tại hoặc đã bị xóa!");
    }
    product.setStatus(Status.DELETED);
    productRepository.save(product);
    return new MsgResponse("Xóa sản phẩm thành công!");
  }
}
