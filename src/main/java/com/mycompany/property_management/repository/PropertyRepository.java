package com.mycompany.property_management.repository;

import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

//    @Query("SELECT p from PropertyEntity p WHERE p.userEntity.id = :userId")
//    List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId);
    List<PropertyEntity> findAllByUserEntityId(Long userId);

}
