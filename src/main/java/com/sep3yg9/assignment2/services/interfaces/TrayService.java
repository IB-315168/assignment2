package com.sep3yg9.assignment2.services.interfaces;

import com.sep3yg9.assignment2.model.TrayEntity;

import java.util.List;

public interface TrayService
{
  TrayEntity create(TrayEntity trayEntity);
  TrayEntity trayFinished(int id);
  TrayEntity putPartIntoTray(int trayId, int partId);
  List<TrayEntity> getAllRemainingTrays();
}
