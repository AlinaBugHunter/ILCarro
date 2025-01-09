package dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@Builder

public class CarDTO_API {

    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private int seats;
    private String carClass;
    private double pricePerDay;
    private String about;
    private String city;
    private String image;

    private double lat; //": 0,
    private double lng; //": 0,
    private String owner; //": "string",

    private List<BookedDTO> bookedPeriods;

}
