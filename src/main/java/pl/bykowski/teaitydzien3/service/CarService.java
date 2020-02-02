package pl.bykowski.teaitydzien3.service;

import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;

import java.util.List;

public interface CarService {
    List<CarResponseDTO> getAllCars();

    CarResponseDTO getCarById(Long id);

    CarResponseDTO addNewCar(CarRequestDTO carRequestDTO);
}
