package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.UserConverter;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> opue = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if(opue.isPresent())
        {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel em = new ErrorModel();
            em.setCode("EMAIL_ALREADY_EXIST");
            em.setMessage("Email with which you are trying to register already exists");
            errorModelList.add(em);

            throw new BusinessException(errorModelList);
        }

        UserEntity ue = userConverter.convertDtoToEntity(userDTO);
        UserEntity usere = userRepository.save(ue);
        userDTO = userConverter.convertEntityToDto(usere);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        Optional<UserEntity> opue = userRepository.findByOwnerEmailAndPassword(email, password);
        UserDTO udto = null;
        if(opue.isPresent())
        {
           udto = userConverter.convertEntityToDto(opue.get());
        }
        else
        {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel em = new ErrorModel();
            em.setCode("INVALID_LOGIN");
            em.setMessage("Incorrect Email or Password");
            errorModelList.add(em);

            throw new BusinessException(errorModelList);
        }
        return udto;
    }
}
