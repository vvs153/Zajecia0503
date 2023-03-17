package com.example.zajecia0503.controller;

import com.example.zajecia0503.model.dto.CarResponse;
import com.example.zajecia0503.model.dto.CreateCarRequest;
import com.example.zajecia0503.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//http://localhost:8080/view/car
//http://localhost:8080/view/car/demo
//http://localhost:8080/view/car/form
@Controller
@RequestMapping("/view/car")
public class CarViewController {
private final CarService carService;

    public CarViewController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/demo")
    public String demo(){
        return "demo-page";
    }
    //Model, ModelView, ModelMap
    @GetMapping()
    public String list(Model model){
        List<CarResponse> listaSamochodow = carService.getAll();
       model.addAttribute("nazwaAtrybutu", listaSamochodow);

        return "car-list-page";
    }

    //formular wysyla => postP
    //przegladarka zawsze dziala na GET
    //JavaScript umozliwa wywolwyanie PATCH,PUT,DELETE
    @GetMapping("/form")
    public String pobierzStroneFormularza(Model model){
        model.addAttribute("atrybutObiektFormularza", new CreateCarRequest());
        return "car-form-page";
    }

    @PostMapping("/form")
    public String przeslijWypelnionyFormularz(Model model, CreateCarRequest request){
        carService.add(request);
        return "redirect:/view/car";
    }
}
