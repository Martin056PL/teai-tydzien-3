package pl.bykowski.teaitydzien3.service;

import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<CarResponseDTO> getAllCars();

    CarResponseDTO getCarById(Long id);

    CarResponseDTO addNewCar(CarRequestDTO carRequestDTO);

    CarResponseDTO replaceAllCarById(CarRequestDTO carRequestDTO, Long id);

    CarResponseDTO editProperValuesCarById(Map<String, Object> map, Long id);

    void deleteCarById(Long id);
}
