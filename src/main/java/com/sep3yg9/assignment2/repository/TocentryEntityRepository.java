package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.TocentryEntity;
import com.sep3yg9.assignment2.model.TocentryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TocentryEntityRepository
    extends JpaRepository<TocentryEntity, TocentryId>
{
}