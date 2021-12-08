package Voronin.MicroService.Lab2.model.responce;

import lombok.Data;

@Data
public class StatisticsResponse {
    private Integer countOfClasses;

    private Integer countOfFirstCategory;
    private Integer countOfSecondCategory;
    private Integer countOfThirdCategory;

    private Integer countOfFirstTypeMark;
    private Integer countOfSecondTypeMark;
    private Integer countOfThirdTypeMark;
}
