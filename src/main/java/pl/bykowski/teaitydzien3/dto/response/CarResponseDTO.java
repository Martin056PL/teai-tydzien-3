package pl.bykowski.teaitydzien3.dto.response;

import lombok.Data;
import pl.bykowski.teaitydzien3.model.CarColor;

@Data
public class CarResponseDTO {

    private Long id;
    private String mark;
    private String model;
    private CarColor color;

}
