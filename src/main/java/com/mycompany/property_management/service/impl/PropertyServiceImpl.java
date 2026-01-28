package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter pconv;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propdto) {

        Optional<UserEntity> oue = userRepository.findById(propdto.getUserId());
        if(oue.isPresent())
        {
            PropertyEntity pe =  pconv.convertDTOtoEntity(propdto);
            pe.setUserEntity(oue.get());
            pe = propertyRepository.save(pe);

            propdto = pconv.convertEntitytoDTO(pe);
        }
        else
        {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel em = new ErrorModel();
            em.setCode("USER ID DOES NOT EXIST ");
            em.setMessage("User does not exist");
            errorModelList.add(em);

            throw new BusinessException(errorModelList);
        }

        return propdto;

    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listpe = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for(PropertyEntity pe : listpe)
        {
            PropertyDTO dto = pconv.convertEntitytoDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listpe = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList = new ArrayList<>();
        for(PropertyEntity pe : listpe)
        {
            PropertyDTO dto = pconv.convertEntitytoDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO pdto, Long propertyId) {
        Optional<PropertyEntity> opEn = propertyRepository.findById(propertyId);
        PropertyDTO pd = null;
        if(opEn.isPresent())
        {
            PropertyEntity pe = opEn.get(); // get data from database
            pe.setTitle(pdto.getTitle());
            pe.setAddress(pdto.getAddress());
            // pe.setOwnerEmail(pdto.getOwnerEmail());
            // pe.setOwnerName(pdto.getOwnerName());
            pe.setDescription(pdto.getDescription());
            pe.setPrice(pdto.getPrice());
            pd = pconv.convertEntitytoDTO(pe);
            propertyRepository.save(pe); // saving in database
        }
        return pd;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO pdto, Long propertyId) {
        Optional<PropertyEntity> opEn = propertyRepository.findById(propertyId);
        PropertyDTO pd = null;
        if(opEn.isPresent())
        {
            PropertyEntity pe = opEn.get(); // get data from database
            pe.setDescription(pdto.getDescription());
            pd = pconv.convertEntitytoDTO(pe);
            propertyRepository.save(pe); // saving in database
        }
        return pd;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO pdto, Long propertyId) {
        Optional<PropertyEntity> opEn = propertyRepository.findById(propertyId);
        PropertyDTO pd = null;
        if(opEn.isPresent())
        {
            PropertyEntity pe = opEn.get(); // get data from database
            pe.setPrice(pdto.getPrice());
            pd = pconv.convertEntitytoDTO(pe);
            propertyRepository.save(pe); // saving in database
        }
        return pd;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
