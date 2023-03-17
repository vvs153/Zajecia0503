package com.example.zajecia0503.controller;

import com.example.zajecia0503.model.Car;
import com.example.zajecia0503.model.dto.CarResponse;
import com.example.zajecia0503.model.dto.CreateCarRequest;
import com.example.zajecia0503.model.dto.UpdateCarRequest;
import com.example.zajecia0503.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/car")
public class CarRestController {
    private final CarService carService;

    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse add(@RequestBody CreateCarRequest request){
        return carService.add(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> list(){
        return carService.getAll();
    }
    //TODO
    //dopisac metode deletemapping adres rest/car/3 usunac car o id 3
    //
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        carService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car findById(@PathVariable Long id){
       return carService.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse updateCar(@PathVariable Long id,  @RequestBody UpdateCarRequest request){
        return carService.update(id,request);
    }
}
