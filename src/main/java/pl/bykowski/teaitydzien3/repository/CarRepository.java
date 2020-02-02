package pl.bykowski.teaitydzien3.repository;

import org.springframework.stereotype.Repository;
import pl.bykowski.teaitydzien3.exception.CarNotFoundException;
import pl.bykowski.teaitydzien3.model.Car;
import pl.bykowski.teaitydzien3.model.CarColor;

import java.util.*;

@Repository
public class CarRepository {

    private List<Car> listOfCars;

    public CarRepository() {
        this.listOfCars = new LinkedList<>();
        listOfCars.add(new Car(1L, "Fiat", "Punto", CarColor.RED));
        listOfCars.add(new Car(2L, "Honda", "Civic", CarColor.GREEN));
        listOfCars.add(new Car(3L, "Audi", "A5", CarColor.BLUE));
        listOfCars.add(new Car(4L, "Toyota", "Yaris", CarColor.BLACK));
    }

    public List<Car> getAllList() {
        return listOfCars;
    }

    public Car getCarById(Long id) {
        Optional<Car> carResult = listOfCars.stream().filter(car -> car.getId().equals(id)).findFirst();
        return carResult.orElseThrow(CarNotFoundException::new);
    }

    public Car addToList(Car car) {
        Long firstFreeId = listOfCars.stream()
                .max(Comparator.comparing(Car::getId))
                .map(Car::getId)
                .orElseThrow(CarNotFoundException::new) + 1L;
        car.setId(firstFreeId);
        listOfCars.add(car);
        return car;
    }

    public Car updateCarById(Car newCar, Long id) {
        int index = listOfCars.indexOf(getCarById(id));
        listOfCars.set(index, newCar);
        return listOfCars.get(index);
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
