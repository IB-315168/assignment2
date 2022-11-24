package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import com.sep3yg9.assignment2.repository.TrayEntityRepository;
import com.sep3yg9.assignment2.services.interfaces.TrayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrayServiceImpl implements TrayService
{
  private final TrayEntityRepository trayEntityRepository;
  private final PartRepository partRepository;

  public TrayServiceImpl(TrayEntityRepository trayEntityRepository, PartRepository partRepository)
  {
    this.trayEntityRepository = trayEntityRepository;
    this.partRepository = partRepository;
  }

  @Override public TrayEntity create(TrayEntity trayEntity) {
    return trayEntityRepository.save(trayEntity);
  }

  @Override public TrayEntity trayFinished(int id) {
    Optional<TrayEntity> trayEntity = trayEntityRepository.findById(id);

    if(trayEntity.isEmpty()) {
      throw new IllegalArgumentException("Tray does not exist");
    }
    else if (trayEntity.get().getFinished())
    {
      throw new IllegalArgumentException("Tray already finished");
    }

    else {
      TrayEntity tray = trayEntity.get();
      tray.setFinished(true);
      trayEntityRepository.save(tray);
    }

    return trayEntity.get();
  }

  @Override public TrayEntity putPartIntoTray(int trayId, int partId) {
    Optional<TrayEntity> trayEntity = trayEntityRepository.findById(trayId);

    if(trayEntity.isEmpty()) {
      throw new IllegalArgumentException("Tray does not exist");
    }

    TrayEntity tray = trayEntity.get();

    if(tray.getFinished()) {
      throw new IllegalArgumentException("Tray is already finished");
    }

    Optional<PartEntity> partEntity = partRepository.findById(partId);

    if(partEntity.isEmpty()) {
      throw new IllegalArgumentException("Part does not exist");
    }

    if(partEntity.get().getFinished()) {
      throw new IllegalArgumentException("Part is already on a tray");
    }

    if(!tray.getType().equalsIgnoreCase(partEntity.get().getType())) {
      throw new IllegalArgumentException("Part type does not match tray type");
    }

    if(tray.checkCarriedWeight() + partEntity.get().getWeight() > tray.getMaxweight()) {
      throw new IllegalArgumentException("This part is too heavy to fit on this tray");
    }

    partEntity.get().setFinished(true);
    partRepository.save(partEntity.get());

    tray.addPart(partEntity.get());
    trayEntityRepository.save(tray);

    return tray;
  }

  @Override public List<TrayEntity> getAllRemainingTrays()
  {
    return trayEntityRepository.findByFinishedFalse();
  }
}
