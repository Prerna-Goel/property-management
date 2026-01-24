package com.mycompany.property_management.converter;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDtoToEntity(UserDTO userDTO)
    {
        UserEntity ue = new UserEntity();
        ue.setOwnerName(userDTO.getOwnerName());
        ue.setOwnerEmail(userDTO.getOwnerEmail());
        ue.setPhone(userDTO.getPhone());
        ue.setPassword(userDTO.getPassword());

        return ue;
    }

    public UserDTO convertEntityToDto(UserEntity userEntity)
    {
        UserDTO ud = new UserDTO();
        ud.setId(userEntity.getId());
        ud.setOwnerEmail(userEntity.getOwnerEmail());
        ud.setOwnerName(userEntity.getOwnerName());
        ud.setPhone(userEntity.getPhone());
        // ud.setPassword(userEntity.getPassword());

        return ud;
    }

}
