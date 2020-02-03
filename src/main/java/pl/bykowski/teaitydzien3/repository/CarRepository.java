package pl.bykowski.teaitydzien3.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.bykowski.teaitydzien3.exception.CarNotFoundException;
import pl.bykowski.teaitydzien3.model.Car;
import pl.bykowski.teaitydzien3.model.CarColor;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CarRepository {


    private final String exceptionMessage;
    private List<Car> listOfCars;

    public CarRepository(@Value("${exception.messages.notFound}") String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
        this.listOfCars = new LinkedList<>();
        listOfCars.add(new Car(1L, "Fiat", "Punto", CarColor.RED));
        listOfCars.add(new Car(2L, "Honda", "Civic", CarColor.GREEN));
        listOfCars.add(new Car(3L, "Saab", "95", CarColor.OTHER));
    }

    public List<Car> getAllCarList() {
        return listOfCars;
    }

    public Car getCarById(Long id) {
        Optional<Car> carResult = listOfCars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
        return carResult.orElseThrow(() -> new CarNotFoundException(exceptionMessage));
    }

    public List<Car> getListOfCarsWithProperColor(CarColor color) {
        return listOfCars.stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Car addCarToList(Car car) {
        Long firstFreeId = listOfCars.stream()
                .max(Comparator.comparing(Car::getId))
                .map(Car::getId)
                .orElseThrow(() -> new CarNotFoundException(exceptionMessage)) + 1L;
        car.setId(firstFreeId);
        listOfCars.add(car);
        return car;
    }

    public Car updateCarById(Car newCar, Long id) {
        int carIndexInTheList = listOfCars.indexOf(getCarById(id));
        listOfCars.set(carIndexInTheList, newCar);
        return listOfCars.get(carIndexInTheList);
    }

    public Car updateProperCarAttributesById(Map<String, Object> map, Long id) {
        Car carFromRepository = getCarById(id);

        if (map.containsKey("mark")) {
            carFromRepository.setMark((String) map.get("mark"));
        }
        if (map.containsKey("model")) {
            carFromRepository.setModel((String) map.get("model"));
        }
        if (map.containsKey("color")) {
            carFromRepository.setColor((CarColor) map.get("color"));
        }

        return updateCarById(carFromRepository, id);
    }

    public void deleteCarById(Long id) {
        listOfCars.remove(getCarById(id));
    }


}
