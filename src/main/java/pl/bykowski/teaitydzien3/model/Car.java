package pl.bykowski.teaitydzien3.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

    private static final long serialVersionUID = 1840725857170163685L;

    private Long id;
    private String mark;
    private String model;
    private CarColor color;
}
