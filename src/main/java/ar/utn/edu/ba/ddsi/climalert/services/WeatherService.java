package ar.utn.edu.ba.ddsi.climalert.services;

import ar.utn.edu.ba.ddsi.climalert.cliente.WeatherApiClient;
import ar.utn.edu.ba.ddsi.climalert.dtos.WeatherApiResponse;
import ar.utn.edu.ba.ddsi.climalert.entities.WeatherRecord;
import ar.utn.edu.ba.ddsi.climalert.repositories.WeatherRecordRepository;
import ar.utn.edu.ba.ddsi.climalert.scheduler.WeatherScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {
    private final WeatherApiClient weatherApiClient;
    private final WeatherRecordRepository weatherRepository;

    public WeatherService(WeatherApiClient weatherApiClient,
                          WeatherRecordRepository weatherRepository) {

        this.weatherApiClient = weatherApiClient;
        this.weatherRepository = weatherRepository;
    }

    public void saveCurrentWeather() {
        WeatherApiResponse response = weatherApiClient.getCurrentWeather();

        WeatherRecord weather = new WeatherRecord();

        weather.setTemperature(response.current().temperature());
        weather.setHumidity(response.current().humidity());
        weather.setCondition(response.current().condition().text());
        weather.setCreatedAt(LocalDateTime.now());

        weatherRepository.save(weather);
    }
}
