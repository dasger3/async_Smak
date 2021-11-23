package Voronin.MicroService.Lab1.Models;

import Voronin.MicroService.Lab1.Models.Enums.Handy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Gun {

    private Integer id;
    private String model;
    private Handy handy;
    private String origin;
    private String material;
    private TTC TTC;
}
