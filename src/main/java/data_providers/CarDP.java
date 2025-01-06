package data_providers;

import dto.CarDTO;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.PropertiesReader.*;

public class CarDP {

    @DataProvider
    public Iterator<CarDTO> CarDPFile_emptyFields() {

        List<CarDTO> carList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/DPCar_emptyFields.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                carList.add(CarDTO.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return carList.listIterator();

    }

    @DataProvider
    public Iterator<CarDTO> CarDPFile_spaceInFields() {

        List<CarDTO> carList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/DPCar_spaceInFields.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                carList.add(CarDTO.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return carList.listIterator();

    }

    @DataProvider
    public Iterator<CarDTO> CarDPFile_properties_invalidSeats() {

        List<CarDTO> carList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + getProperty("login.properties", "fileDPCar")));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                carList.add(CarDTO.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return carList.listIterator();

    }

    @DataProvider
    public Iterator<CarDTO> CarDPFile_invalidPrice() {

        List<CarDTO> carList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/DPCar_invalidPrice.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                carList.add(CarDTO.builder()
                        .city(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .serialNumber(splitArray[7])
                        .pricePerDay(Double.parseDouble(splitArray[8]))
                        .about(splitArray[9])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return carList.listIterator();

    }

}
