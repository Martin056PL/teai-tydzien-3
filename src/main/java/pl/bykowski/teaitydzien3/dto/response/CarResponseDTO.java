package pl.bykowski.teaitydzien3.dto.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import pl.bykowski.teaitydzien3.model.CarColor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CarResponseDTO extends RepresentationModel<CarResponseDTO> {

    private Long id;
    private String mark;
    private String model;
    private CarColor color;



}
