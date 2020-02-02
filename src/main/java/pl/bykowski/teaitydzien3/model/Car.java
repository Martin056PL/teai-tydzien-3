package pl.bykowski.teaitydzien3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    private static final long serialVersionUID = 1840725857170163685L;

    private Long id;
    private String mark;
    private String model;
    private CarColor color;

}
