package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.model.TocentryEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
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
    if(!(productEntity.getType().equalsIgnoreCase("same parts") || productEntity.getType().equalsIgnoreCase("half an animal"))) {
      throw new IllegalArgumentException("Product must be of either \"Same parts\" or \"Half an animal type\"");
    }

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

    if(product.getType().equalsIgnoreCase("same parts")) {
      if(product.getTocentries().size() != 0) {
        if(!product.getTocentries().stream().toList().get(0).getIdpart().getType().equalsIgnoreCase(part.getType())) {
          throw new IllegalArgumentException("This product requires same type of parts");
        }
      }
    } else {
      if(product.getTocentries().size() != 0) {
        for(TocentryEntity toc : product.getTocentries()) {
          if(toc.getIdpart().getType().equalsIgnoreCase(part.getType())) {
            throw new IllegalArgumentException("This product requires that there is only one item of each type");
          }
        }
      }
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
