package pl.bykowski.teaitydzien3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;
import pl.bykowski.teaitydzien3.service.CarService;
import pl.bykowski.teaitydzien3.service.HateoasCarService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class CarController {

    private final CarService carService;
    private final HateoasCarService hateoasCarService;

    @Autowired
    public CarController(CarService carService, HateoasCarService hateoasCarService) {
        this.carService = carService;
        this.hateoasCarService = hateoasCarService;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> list = carService.getAllCars();
        return ResponseEntity.ok(hateoasCarService.addLinkToListOfDTOs(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<CarResponseDTO>> getCarById(@PathVariable Long id) {
        CarResponseDTO dto = carService.getCarById(id);
        return ResponseEntity.ok(hateoasCarService.addLinkToSingleDTO(dto));
    }

    @GetMapping(params = "color")
    public ResponseEntity<List<CarResponseDTO>> getListOfCarsByColor(@RequestParam String color) {
        return ResponseEntity.ok(carService.getListOfCarsByColor(color));
    }

    @PostMapping()
    public ResponseEntity<CarResponseDTO> addCar(@RequestBody CarRequestDTO carRequestDTO) {
        return ResponseEntity.of(Optional.of(carService.addNewCar(carRequestDTO)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarResponseDTO> editCarById(@RequestBody CarRequestDTO carRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(carService.replaceAllCarById(carRequestDTO, id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CarResponseDTO> editProperCarAttributes(@RequestBody Map<String, Object> map, @PathVariable Long id) {
        return ResponseEntity.ok(carService.updateProperCarAttributesById(map, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
