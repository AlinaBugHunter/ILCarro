package data_providers;

import dto.CarDTO;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarDP {

    @DataProvider
    public Iterator<CarDTO> CarDPFile() {

        List<CarDTO> carList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/data_car.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(";");
                carList.add(CarDTO.builder()
                        .city(splitArray[0])
                        .serialNumber(splitArray[1])
                        .manufacture(splitArray[2])
                        .model(splitArray[3])
                        .year(splitArray[4])
                        .fuel(splitArray[5])
                        .seats(Integer.parseInt(splitArray[6]))
                        .carClass(splitArray[7])
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
