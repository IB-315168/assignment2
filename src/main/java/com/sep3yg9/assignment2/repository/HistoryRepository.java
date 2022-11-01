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
import java.util.HashMap;
import java.util.Map;

@Repository public class HistoryRepository
{
  private Map<Long, Animal> animals = new HashMap<>();
  private Map<Long, PartEntity> parts = new HashMap<>();
  private Map<Long, TrayEntity> trays = new HashMap<>();
  private Map<Long, ProductEntity> products = new HashMap<>();

  private ObjectMapper mapper = new ObjectMapper();

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

  public void addToTrayHistory(TrayEntity entity) {
    trays.put(entity.getId(), entity);
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

  public Map<Long, TrayEntity> getTrays()
  {
    return trays;
  }

  public Map<Long, ProductEntity> getProducts()
  {
    return products;
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
      File traysHistory = new File("./historyfiles/trays.txt");
      if (!traysHistory.createNewFile())
      {
        trays = mapper.readValue(traysHistory,
            new TypeReference<HashMap<Long, TrayEntity>>()
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
