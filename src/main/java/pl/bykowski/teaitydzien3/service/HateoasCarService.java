package pl.bykowski.teaitydzien3.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;

import java.util.List;

public interface HateoasCarService {
    EntityModel<CarResponseDTO> addLinkToSingleDTO(CarResponseDTO carResponseDTO);

    CollectionModel<CarResponseDTO> addLinkToListOfDTOs(List<CarResponseDTO> carResponseDTOList);
}
