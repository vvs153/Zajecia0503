package com.example.zajecia0503.service;

import com.example.zajecia0503.model.Car;
import com.example.zajecia0503.model.dto.CarResponse;
import com.example.zajecia0503.model.dto.CreateCarRequest;
import com.example.zajecia0503.model.dto.UpdateCarRequest;
import com.example.zajecia0503.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository; //musi byc konstruktor  po final

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse add(CreateCarRequest request) {
        // na podsatawie obiektu zadania (request) tworzymy obiekt zadany Car
        Car car = Car.builder()
                .reg(request.getNrRejestracyjny())
                .engineCap(request.getPojemnoscSilnika())
                .registrationDate(request.getDataRejestracji())
                .doors(request.getIloscDrzwi())
                .build();
        car= carRepository.save(car); // zapis do bazy

        return mapujCarNaCarResponse(car);
    }

    public List<CarResponse> getAll() {

        return carRepository.findAll().stream()
                .map(this::mapujCarNaCarResponse).toList();
    }
    private CarResponse mapujCarNaCarResponse(Car car){
        return new CarResponse(
                car.getId(),
                car.getReg(),
                car.getRegistrationDate(),
                car.getDoors(),
                car.getEngineCap()
        );
    }

    public void delete(Long carId) {
        carRepository.deleteById(carId);
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("nie znaleziono auta o takim id"));
    }

    public CarResponse update(Long id, UpdateCarRequest request) {
       Car car =  carRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("nie znaleziono auto o takim id"));
        if(request.getNrRejestracyjny() != null){
           car.setReg(request.getNrRejestracyjny());
        }
        if(request.getIloscDrzwi() != null){
            car.setDoors(request.getIloscDrzwi());
        }
        if(request.getPojemnoscSilnika() != null){
            car.setEngineCap(request.getPojemnoscSilnika());
        }
        car = carRepository.save(car);
        return carToCarResponse(car);
    }

    private CarResponse carToCarResponse(Car car) {
        return new CarResponse(
                car.getId(), car.getReg(), car.getRegistrationDate(), car.getDoors(), car.getEngineCap()
        );

    }
}
