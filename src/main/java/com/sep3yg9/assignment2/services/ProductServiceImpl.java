package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.grpc.protobuf.products.PartProduct;
import com.sep3yg9.assignment2.model.dbentities.PartEntity;
import com.sep3yg9.assignment2.model.dbentities.ProductEntity;
import com.sep3yg9.assignment2.model.dbentities.TocentryEntity;
import com.sep3yg9.assignment2.model.dbentities.TrayEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import com.sep3yg9.assignment2.repository.ProductEntityRepository;
import com.sep3yg9.assignment2.repository.TocentryEntityRepository;
import com.sep3yg9.assignment2.repository.TrayEntityRepository;
import com.sep3yg9.assignment2.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
  private final ProductEntityRepository productEntityRepository;
  private final PartRepository partRepository;
  private final TrayEntityRepository trayEntityRepository;
  private final TocentryEntityRepository tocentryEntityRepository;

  public ProductServiceImpl(ProductEntityRepository productEntityRepository, PartRepository partRepository, TrayEntityRepository trayEntityRepository, TocentryEntityRepository tocentryEntityRepository)
  {
    this.productEntityRepository = productEntityRepository;
    this.partRepository = partRepository;
    this.trayEntityRepository = trayEntityRepository;
    this.tocentryEntityRepository = tocentryEntityRepository;
  }

  @Override public ProductEntity create(ProductEntity productEntity)
  {
    return productEntityRepository.save(productEntity);
  }

  @Override public ProductEntity putPartIntoProduct(int productId, int partId)
  {
    Optional<ProductEntity> productEntity = productEntityRepository.findById(productId);

    if(productEntity.isEmpty()) {
      throw new IllegalArgumentException("Product does not exist");
    }

    ProductEntity product = productEntity.get();

    if(product.getFinished()) {
      throw new IllegalArgumentException("Product is already finished");
    }

    Optional<PartEntity> partEntity = partRepository.findById(partId);

    if(partEntity.isEmpty()) {
      throw new IllegalArgumentException("Part does not exist");
    }

    if(!partEntity.get().getFinished()) {
      throw new IllegalArgumentException("Part is not yet finished");
    }

    PartEntity part = partEntity.get();

    Optional<TrayEntity> trayEntity = trayEntityRepository.findByParttrays_Id_PartId(partId);

    if(trayEntity.isEmpty()) {
      throw new IllegalArgumentException("Tray does not exist");
    }

    if(!trayEntity.get().getFinished()) {
      throw new IllegalArgumentException("Tray is not finished");
    }

    TrayEntity tray = trayEntity.get();

    TocentryEntity entry = new TocentryEntity();

    entry.setIdproduct(product);
    entry.setIdpart(part);
    entry.setIdtray(tray);

    tocentryEntityRepository.save(entry);

    product.getTocentries().add(entry);

    return productEntityRepository.save(product);
  }

  @Override public ProductEntity productFinished(int id) {
    Optional<ProductEntity> productEntity = productEntityRepository.findById(id);

    if(productEntity.isEmpty()) {
      throw new IllegalArgumentException("Product does not exist");
    }
    else if (productEntity.get().getFinished())
    {
      throw new IllegalArgumentException("Product already finished");
    }

    else {
      ProductEntity product = productEntity.get();
      product.setFinished(true);
      productEntityRepository.save(product);
    }

    return productEntity.get();
  }


  @Override public List<TrayEntity> getAllFinishedTrays() {
    return trayEntityRepository.findByFinishedTrue();
  }

  @Override public List<ProductEntity> getAllUnfinishedProducts() {
    return productEntityRepository.findByFinishedFalse();
  }

}
