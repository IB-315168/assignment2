package com.sep3yg9.assignment2.services.interfaces;

import com.sep3yg9.assignment2.model.ProductEntity1;
import com.sep3yg9.assignment2.model.dbentities.ProductEntity;

import java.util.List;

public interface HistoryService
{
  List<ProductEntity> getAnimalsProducts(int id);
  List<Integer> getProductsAnimals(int id);
}
