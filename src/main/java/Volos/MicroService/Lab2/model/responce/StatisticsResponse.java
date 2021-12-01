package Volos.MicroService.Lab2.model.responce;

import lombok.Data;

@Data
public class StatisticsResponse {
    private String nameOfConcert;
    private Integer countOfTickets;

    private Integer countOfFirstTypeOfTickets;
    private Integer countOfSecondTypeOfTickets;
    private Integer countOfThirdTypeOfTickets;

    @Override
    public String toString() {
        return "StatisticsResponse{" +
                "Name: '" + nameOfConcert + '\'' +
                ", all sold tickets" + countOfTickets +
                ", Normal: " + countOfFirstTypeOfTickets +
                ", Cool: " + countOfSecondTypeOfTickets +
                ", VIP: " + countOfThirdTypeOfTickets +
                '}';
    }
}
