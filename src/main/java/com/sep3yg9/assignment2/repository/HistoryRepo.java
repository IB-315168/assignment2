package com.sep3yg9.assignment2.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.model.PartEntity1;
import com.sep3yg9.assignment2.model.ProductEntity1;
import com.sep3yg9.assignment2.model.TrayEntity1;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository public class HistoryRepo
{
  private Map<Long, Animal> animals = new HashMap<>();
  private Map<Long, PartEntity1> parts = new HashMap<>();
  private Map<Long, ArrayList<TrayEntity1>> trays = new HashMap<>();
  private Map<Long, ProductEntity1> products = new HashMap<>();

  private final ObjectMapper mapper = new ObjectMapper();

  public HistoryRepo()
  {
    initDataSource();
  }

  public void addToAnimalHistory(Animal animal) {
    animals.put(animal.getRegNumber(), animal);
    saveChanges();
  }

  public void addToPartHistory(PartEntity1 entity) {
    parts.put(entity.getId(), entity);
    saveChanges();
  }

  public void addNewTray(long id) {
    trays.put(id, new ArrayList<>());
  }

  public void addToTrayHistory(TrayEntity1 entity) {
    trays.get(entity.getId()).add(entity);
    saveChanges();
  }

  public void addToProductHistory(ProductEntity1 entity) {
    products.put(entity.getId(), entity);
    saveChanges();
  }

  public Map<Long, Animal> getAnimals()
  {
    return animals;
  }

  public Map<Long, PartEntity1> getParts()
  {
    return parts;
  }

  public Map<Long, ArrayList<TrayEntity1>> getTrays()
  {
    return trays;
  }

  public Map<Long, ProductEntity1> getProducts()
  {
    return products;
  }

  public List<Long> getProductsAnimals(long id) {
    List<Long> animalIds = new ArrayList<>();
    ProductEntity1 product = products.get(id);

    for(PartEntity1 part : product.getParts()) {
      animalIds.add(part.getAnimal_id());
    }

    return animalIds;
  }

  public List<ProductEntity1> getAnimalsProducts(long id) {
    List<ProductEntity1> productEntityList = new ArrayList<>();

    List<PartEntity1> selectedParts = new ArrayList<>();
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
    return products.keySet().size() - 1;
  }

  public long getLastPartId() {
    return parts.keySet().size() - 1;
  }

  public long getLastTrayId() {
    return trays.keySet().size() - 1;
  }

  public long getLastAnimalId() {
    return animals.keySet().size() - 1;
  }
  private void initDataSource()
  {
    try
    {
      File partsHistory = new File("./historyfiles/animals.txt");
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
      File partsHistory = new File("./historyfiles/parts.txt");
      if (!partsHistory.createNewFile())
      {
        parts = mapper.readValue(partsHistory,
            new TypeReference<HashMap<Long, PartEntity1>>()
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
      File traysHistory = new File("./historyfiles/trays.txt");
      if (!traysHistory.createNewFile())
      {
        trays = mapper.readValue(traysHistory,
            new TypeReference<HashMap<Long, ArrayList<TrayEntity1>>>()
            {
            });
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    try
    {
      File productsHistory = new File("./historyfiles/products.txt");
      if (!productsHistory.createNewFile())
      {
        products = mapper.readValue(productsHistory, new TypeReference<HashMap<Long, ProductEntity1>>()
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
      FileWriter writer = new FileWriter("./historyfiles/animals.txt", false);
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
      FileWriter writer = new FileWriter("./historyfiles/parts.txt", false);
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
      FileWriter writer = new FileWriter("./historyfiles/trays.txt", false);
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
      FileWriter writer = new FileWriter("./historyfiles/products.txt", false);
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
