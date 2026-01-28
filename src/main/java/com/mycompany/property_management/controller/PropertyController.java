package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pms.dummy}")
    private String dummy;

    @Value("${spring.database.url:}")
    private String surl;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO pdto)
    {
        pdto = propertyService.saveProperty(pdto);
        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(pdto, HttpStatus.CREATED);
        return re;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties()
    {
        // System.out.println(dummy);
        // System.out.println(surl);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> re = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return re;
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable("userId") Long id)
    {
        List<PropertyDTO> propertyList = propertyService.getAllPropertiesForUser(id);
        ResponseEntity<List<PropertyDTO>> re = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return re;
    }

    @PutMapping("/properties/{pid}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO pdto, @PathVariable Long pid)
    {
        pdto = propertyService.updateProperty(pdto,pid);
        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(pdto, HttpStatus.CREATED);
        return re;
    }

    @PatchMapping("/properties/update-description/{pid}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO pdto, @PathVariable Long pid)
    {
        pdto = propertyService.updatePropertyDescription(pdto,pid);
        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(pdto, HttpStatus.OK);
        return re;
    }

    @PatchMapping("/properties/update-price/{pid}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO pdto, @PathVariable Long pid)
    {
        pdto = propertyService.updatePropertyPrice(pdto,pid);
        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(pdto, HttpStatus.OK);
        return re;
    }

    @DeleteMapping("/properties/delete/{pid}")
    public ResponseEntity deleteProperty(@PathVariable Long pid)
    {
        propertyService.deleteProperty(pid);
        ResponseEntity<Void> re = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return re;
        // return new ResponseEntity.noContent();
    }
}
