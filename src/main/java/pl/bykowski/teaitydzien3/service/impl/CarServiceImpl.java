package pl.bykowski.teaitydzien3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;
import pl.bykowski.teaitydzien3.exception.CarNotFoundException;
import pl.bykowski.teaitydzien3.model.Car;
import pl.bykowski.teaitydzien3.model.CarColor;
import pl.bykowski.teaitydzien3.repository.CarRepository;
import pl.bykowski.teaitydzien3.service.CarService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final String exceptionMessage;
    private final ModelMapper modelMapper;
    private final CarRepository repository;

    @Autowired
    public CarServiceImpl(ModelMapper modelMapper, CarRepository repository, @Value("${exception.messages.notFound}") String exceptionMessage) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public List<CarResponseDTO> getAllCars() {
        List<Car> carList = repository.getAllCarList();
        return carList.stream()
                .map(car -> modelMapper.map(car, CarResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarResponseDTO getCarById(Long id) {
        Car car = repository.getCarById(id);
        return modelMapper.map(car, CarResponseDTO.class);
    }

    @Override
    public List<CarResponseDTO> getListOfCarsByColor(String color) {
        CarColor carColorEnum;
        try {
            carColorEnum = CarColor.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CarNotFoundException(exceptionMessage);
        }

        List<Car> carListWithProperColor = repository.getListOfCarsWithProperColor(carColorEnum);
        return carListWithProperColor.stream()
                .map(car -> modelMapper.map(car, CarResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarResponseDTO addNewCar(CarRequestDTO carRequestDTO) {
        Car car = repository.addCarToList(modelMapper.map(carRequestDTO, Car.class));
        return modelMapper.map(car, CarResponseDTO.class);
    }

    @Override
    public CarResponseDTO replaceAllCarById(CarRequestDTO carRequestDTO, Long id) {
        Car carFromRepository = repository.getCarById(id);
        Car updatedCar = resignAttributesFromDtoToEntity(modelMapper.map(carRequestDTO, Car.class), carFromRepository);
        Car savedCar = repository.updateCarById(updatedCar, id);
        return modelMapper.map(savedCar, CarResponseDTO.class);
    }

    private Car resignAttributesFromDtoToEntity(Car carFromDto, Car carFromRepository) {
        carFromRepository.setColor(carFromDto.getColor());
        carFromRepository.setMark(carFromDto.getMark());
        carFromRepository.setModel(carFromDto.getModel());
        return carFromRepository;
    }

    @Override
    public CarResponseDTO updateProperCarAttributesById(Map<String, Object> map, Long id) {
        return modelMapper.map(repository.updateProperCarAttributesById(map, id), CarResponseDTO.class);
    }

    @Override
    public void deleteCarById(Long id) {
        repository.deleteCarById(id);
    }
}
