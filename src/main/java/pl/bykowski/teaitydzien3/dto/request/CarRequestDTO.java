package pl.bykowski.teaitydzien3.dto.request;

import lombok.Data;
import pl.bykowski.teaitydzien3.model.CarColor;

@Data
public class CarRequestDTO {

    private String mark;
    private String model;
    private CarColor color;
}
