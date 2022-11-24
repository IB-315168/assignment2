package com.sep3yg9.assignment2.services.interfaces;

import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.model.TrayEntity;

import java.util.List;

public interface ProductService
{
  ProductEntity create(ProductEntity productEntity);
  ProductEntity putPartIntoProduct(int productId, int partId);
  ProductEntity productFinished(int id);
  List<TrayEntity> getAllFinishedTrays();
  List<ProductEntity> getAllUnfinishedProducts();

}
