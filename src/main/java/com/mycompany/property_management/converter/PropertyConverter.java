package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO pdto){

        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(pdto.getTitle());
        pe.setAddress(pdto.getAddress());
        pe.setOwnerEmail(pdto.getOwnerEmail());
        pe.setOwnerName(pdto.getOwnerName());
        pe.setDescription(pdto.getDescription());
        pe.setPrice(pdto.getPrice());

        return pe;
    }

    public PropertyDTO convertEntitytoDTO(PropertyEntity pent){

        PropertyDTO pdto = new PropertyDTO();
        pdto.setId(pent.getId());
        pdto.setTitle(pent.getTitle());
        pdto.setAddress(pent.getAddress());
        pdto.setOwnerEmail(pent.getOwnerEmail());
        pdto.setOwnerName(pent.getOwnerName());
        pdto.setDescription(pent.getDescription());
        pdto.setPrice(pent.getPrice());

        return pdto;
    }
}
