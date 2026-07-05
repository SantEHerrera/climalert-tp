package ar.utn.edu.ba.ddsi.climalert.scheduler;


import ar.utn.edu.ba.ddsi.climalert.services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {
    private final WeatherService weatherService;

    public WeatherScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 300000)
    public void collectWeather() {
        weatherService.saveCurrentWeather();
    }
}
