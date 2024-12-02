package ru.project.big_data.geo_service.data;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataGenerator {


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private StringBuilder generateNumber() {
        StringBuilder result = new StringBuilder("\"number\":\"");
        return result.append("+79").append(random.nextLong(19_999_99_99l, 99_999_99_99l))
                .append("\"");
    }

    private StringBuilder getDateTime() {
        StringBuilder result = new StringBuilder("\"datetime\":\"");
        return result.append(dateTimeFormatter.format(LocalDateTime.now()))
                .append("\"");
    }

    private StringBuilder generateGeo() {
        StringBuilder result = new StringBuilder("\"lat\":\"");
        return result
                .append(random.nextDouble(-90, 90))
                .append("\",")
                .append("\"lng\":\"")
                .append(random.nextDouble(-180, 180))
                .append("\"");
    }

    public String getData() {
        String result = new StringBuilder("{")
                .append(generateNumber())
                .append(",")
                .append(getDateTime())
                .append(",")
                .append(generateGeo())
                .append("}").toString();
//        TODO
        System.out.println(result);
        return result;
    }
}
