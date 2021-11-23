package Voronin.MicroService.Lab1.Models;

import Voronin.MicroService.Lab1.Models.Enums.Range;
import lombok.Data;

@Data
public class TTC {

    private Range range;
    private Double sightingRange;
    private boolean hasClip;
    private boolean hasOptics;
}
