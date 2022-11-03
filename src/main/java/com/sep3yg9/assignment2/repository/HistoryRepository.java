package com.sep3yg9.assignment2.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Repository public class HistoryRepository
{

  private Map<Long, Animal> animals = new HashMap<>();
  private Map<Long, PartEntity> parts = new HashMap<>();
  private Map<Long, ArrayList<TrayEntity>> trays = new HashMap<>();
  private Map<Long, ProductEntity> products = new HashMap<>();

  private final ObjectMapper mapper = new ObjectMapper();

  public HistoryRepository()
  {
    initDataSource();
  }

  public void addToAnimalHistory(Animal animal) {
    animals.put(animal.getRegNumber(), animal);
    saveChanges();
  }

  public void addToPartHistory(PartEntity entity) {
    parts.put(entity.getId(), entity);
    saveChanges();
  }

  public void addNewTray(long id) {
    trays.put(id, new ArrayList<>());
    saveChanges();
  }

  public void addToTrayHistory(TrayEntity entity) {
    trays.get(entity.getId()).add(entity);
    saveChanges();
  }

  public void addToProductHistory(ProductEntity entity) {
    products.put(entity.getId(), entity);
    saveChanges();
  }

  public Map<Long, Animal> getAnimals()
  {
    return animals;
  }

  public Map<Long, PartEntity> getParts()
  {
    return parts;
  }

  public Map<Long, ArrayList<TrayEntity>> getTrays()
  {
    return trays;
  }

  public Map<Long, ProductEntity> getProducts()
  {
    return products;
  }

  public List<Long> getProductsAnimals(long id) {
    List<Long> animalIds = new ArrayList<>();
    ProductEntity product = products.get(id);

    for(PartEntity part : product.getParts()) {
      animalIds.add(part.getAnimal_id());
    }

    return animalIds;
  }

  public List<ProductEntity> getAnimalsProducts(long id) {
    List<ProductEntity> productEntityList = new ArrayList<>();

    List<PartEntity> selectedParts = new ArrayList<>();
    for(long idPart : parts.keySet()) {
      if(parts.get(idPart).getAnimal_id() == id) {
        selectedParts.add(parts.get(idPart));
      }
    }

    for(long idProduct : products.keySet()) {
      if(products.get(idProduct).getParts().stream().anyMatch(selectedParts::contains)) {
        productEntityList.add(products.get(idProduct));
      }
    }

    return productEntityList;
  }

  public long getLastProductId() {
    if(products.size() != 0) {
      return Collections.max(products.keySet());
    }
    return 0;
  }

  public long getLastPartId() {
    if(parts.size() != 0) {
      return Collections.max(parts.keySet());
    }
    return 0;
  }

  public long getLastTrayId() {
    if(trays.size() != 0) {
      return Collections.max(trays.keySet());
    }
    return 0;
  }

  public long getLastAnimalId() {
    if(animals.size() != 0) {
      return Collections.max(animals.keySet());
    }
    return 0;
  }
  private void initDataSource()
  {
    try
    {
      File partsHistory = new File("./assignment2/historyfiles/animals.txt");
      if (!partsHistory.createNewFile())
      {
        animals = mapper.readValue(partsHistory,
            new TypeReference<HashMap<Long, Animal>>()
            {
            });
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try
    {
      File partsHistory = new File("./assignment2/historyfiles/parts.txt");
      if (!partsHistory.createNewFile())
      {
        parts = mapper.readValue(partsHistory,
            new TypeReference<HashMap<Long, PartEntity>>()
            {
            });
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try
    {
      File traysHistory = new File("./assignment2/historyfiles/trays.txt");
      if (!traysHistory.createNewFile())
      {
        trays = mapper.readValue(traysHistory,
            new TypeReference<HashMap<Long, ArrayList<TrayEntity>>>()
            {
            });
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    try
    {
      File productsHistory = new File("./assignment2/historyfiles/products.txt");
      if (!productsHistory.createNewFile())
      {
        products = mapper.readValue(productsHistory, new TypeReference<HashMap<Long, ProductEntity>>()
        {
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveChanges() {
    try
    {
      FileWriter writer = new FileWriter("./assignment2/historyfiles/animals.txt", false);
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(animals);
      writer.write(json);
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try
    {
      FileWriter writer = new FileWriter("./assignment2/historyfiles/parts.txt", false);
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parts);
      writer.write(json);
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try
    {
      FileWriter writer = new FileWriter("./assignment2/historyfiles/trays.txt", false);
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(trays);
      writer.write(json);
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    try
    {
      FileWriter writer = new FileWriter("./assignment2/historyfiles/products.txt", false);
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
      writer.write(json);
      writer.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
