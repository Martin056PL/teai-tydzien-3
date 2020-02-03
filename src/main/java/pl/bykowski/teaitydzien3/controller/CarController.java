package pl.bykowski.teaitydzien3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;
import pl.bykowski.teaitydzien3.model.CarColor;
import pl.bykowski.teaitydzien3.service.CarService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping(params = "color")
    public ResponseEntity<List<CarResponseDTO>> getListOfCarsByColor(@RequestParam String color){
        return ResponseEntity.ok(carService.getListOfCarsByColor(color));
    }

    @PostMapping()
    public ResponseEntity<CarResponseDTO> addCar(@RequestBody CarRequestDTO carRequestDTO) {
        return ResponseEntity.of(Optional.of(carService.addNewCar(carRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> editCarById(@RequestBody CarRequestDTO carRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(carService.replaceAllCarById(carRequestDTO, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponseDTO> editProperCarAttributes(@RequestBody Map<String, Object> map, @PathVariable Long id) {
        return ResponseEntity.ok(carService.editProperValuesCarById(map, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
