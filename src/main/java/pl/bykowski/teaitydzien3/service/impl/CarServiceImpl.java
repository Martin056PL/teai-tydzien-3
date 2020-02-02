package pl.bykowski.teaitydzien3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bykowski.teaitydzien3.dto.request.CarRequestDTO;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;
import pl.bykowski.teaitydzien3.model.Car;
import pl.bykowski.teaitydzien3.repository.CarRepository;
import pl.bykowski.teaitydzien3.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private ModelMapper modelMapper;
    private CarRepository repository;

    @Autowired
    public CarServiceImpl(ModelMapper modelMapper, CarRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public List<CarResponseDTO> getAllCars(){
        List<Car> carList = repository.getAllList();
        return carList.stream().map(car -> modelMapper.map(car, CarResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarResponseDTO getCarById(Long id){
        Car car = repository.getCarById(id);
        return modelMapper.map(car, CarResponseDTO.class);
    }

    @Override
    public CarResponseDTO addNewCar(CarRequestDTO carRequestDTO){
        Car car = repository.addToList(modelMapper.map(carRequestDTO, Car.class));
        return modelMapper.map(car,CarResponseDTO.class);
    }
}
