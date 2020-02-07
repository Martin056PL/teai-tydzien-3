package pl.bykowski.teaitydzien3.service.impl;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import pl.bykowski.teaitydzien3.controller.CarController;
import pl.bykowski.teaitydzien3.dto.response.CarResponseDTO;
import pl.bykowski.teaitydzien3.service.HateoasCarService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class HateoasCarServiceImpl implements HateoasCarService {

    @Override
    public EntityModel<CarResponseDTO> addLinkToSingleDTO(CarResponseDTO carResponseDTO) {
        EntityModel<CarResponseDTO> car = new EntityModel<>(carResponseDTO);
        Link link = linkTo(CarController.class).slash(carResponseDTO.getId()).withSelfRel();
        car.add(link);
        return car;
    }

    @Override
    public CollectionModel<CarResponseDTO> addLinkToListOfDTOs(List<CarResponseDTO> carResponseDTOList) {
        carResponseDTOList
                .forEach(carResponseDTO -> carResponseDTO.add(
                        linkTo(CarController.class).slash(carResponseDTO.getId()).withSelfRel()
                        )
                );
        CollectionModel<CarResponseDTO> carResponseDTOS = new CollectionModel<>(carResponseDTOList);
        carResponseDTOS.add(linkTo(CarController.class).withSelfRel());

        return carResponseDTOS;
    }

}
