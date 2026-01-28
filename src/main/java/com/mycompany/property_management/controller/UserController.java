package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description="This method is going to be used for user registration")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Parameter(
            name = "userDTO",
            example = "user information",
            required = true
    ) @Valid @RequestBody UserDTO udto)
    {
        udto = userService.register(udto);
        return new ResponseEntity<>(udto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO udto)
    {
        udto = userService.login(udto.getOwnerEmail(), udto.getPassword());
        return new ResponseEntity<>(udto, HttpStatus.OK);
    }
}
