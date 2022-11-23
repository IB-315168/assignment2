package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.model.dbentities.AnimalEntity;
import com.sep3yg9.assignment2.model.dbentities.ProductEntity;
import com.sep3yg9.assignment2.model.dbentities.TocentryEntity;
import com.sep3yg9.assignment2.repository.AnimalRepository;
import com.sep3yg9.assignment2.repository.ProductEntityRepository;
import com.sep3yg9.assignment2.services.interfaces.HistoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service public class HistoryServiceImpl implements HistoryService
{
  private final AnimalRepository animalRepository;
  private final ProductEntityRepository productEntityRepository;

  public HistoryServiceImpl(AnimalRepository animalRepository,
      ProductEntityRepository productEntityRepository)
  {
    this.animalRepository = animalRepository;
    this.productEntityRepository = productEntityRepository;
  }

  @Override public List<ProductEntity> getAnimalsProducts(int id)
  {
    Optional<AnimalEntity> animalEntity = animalRepository.findById(id);

    if (animalEntity.isEmpty())
    {
      throw new IllegalArgumentException("Animal does not exist");
    }

    AnimalEntity animal = animalEntity.get();

    return productEntityRepository.findByTocentries_Idpart_Animalid(animal);
  }

  @Override public List<Integer> getProductsAnimals(int id)
  {
    List<Integer> animalIds = new ArrayList<>();

    Optional<ProductEntity> productEntity = productEntityRepository.findById(
        id);

    if (productEntity.isEmpty())
    {
      throw new IllegalArgumentException("Product does not exist");
    }

    ProductEntity product = productEntity.get();

    for (TocentryEntity toc : product.getTocentries())
    {
      animalIds.add(toc.getIdpart().getAnimalid().getId());
    }

    return animalIds;
  }
}
