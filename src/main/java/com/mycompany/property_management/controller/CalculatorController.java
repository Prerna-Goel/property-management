package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add/{n3}")
    public Double add(@PathVariable("n3") Double num1, @RequestParam("n2") Double num2)
    {
        return num1+num2;
    }

    @GetMapping("/sub/{n1}/{n2}")
    public Double substract(@PathVariable("n1") Double num1, @PathVariable("n2") Double num2)
    {
        Double res = null;

        if(num1 > num2)
        {
            res = num1-num2;
        }
        else
        {
            res = num2-num1;
        }

        return res;
    }

    @PostMapping("/multi")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO cdto)
    {
        Double res = null;
        res = cdto.getNum1() * cdto.getNum2() * cdto.getNum3() * cdto.getNum4();
        ResponseEntity<Double> resE = new ResponseEntity<Double>(res, HttpStatus.CREATED);
        return resE;
    }
}
