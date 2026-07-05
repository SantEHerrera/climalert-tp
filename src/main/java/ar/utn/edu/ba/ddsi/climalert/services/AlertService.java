package ar.utn.edu.ba.ddsi.climalert.services;

import ar.utn.edu.ba.ddsi.climalert.entities.Alert;
import ar.utn.edu.ba.ddsi.climalert.entities.WeatherRecord;
import ar.utn.edu.ba.ddsi.climalert.repositories.AlertRepository;
import ar.utn.edu.ba.ddsi.climalert.repositories.WeatherRecordRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlertService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AlertService.class);
    private final WeatherRecordRepository weatherRepository;
    private final AlertRepository alertRepository;
    private final MailService emailService;

    public AlertService(WeatherRecordRepository weatherRepository,
                        AlertRepository alertRepository,
                        MailService emailService) {

        this.weatherRepository = weatherRepository;
        this.alertRepository = alertRepository;
        this.emailService = emailService;
    }

    public void processLastWeatherRecord() {

        Optional<WeatherRecord> optionalWeather =
                weatherRepository.findFirstByOrderByCreatedAtDesc();

        if (optionalWeather.isEmpty()) {
            return;
        }

        WeatherRecord weather = optionalWeather.get();

        log.info("la temperatura es {} y la humendad es {}",weather.getTemperature(),
                    weather.getHumidity());

        if (weather.getTemperature() > 35 &&
                weather.getHumidity() > 65) {

            Alert alert = new Alert();

            alert.setGeneratedAt(LocalDateTime.now());

            alert.setMessage(buildAlertMessage(weather));

            alertRepository.save(alert);

            emailService.sendAlert(alert.getMessage());

        }
    }
    private String buildAlertMessage(WeatherRecord weather) {

        return """
                ALERTA CLIMÁTICA

                Temperatura: %.1f °C
                Humedad: %d %%
                Condición: %s
                Fecha: %s
                """
                .formatted(
                        weather.getTemperature(),
                        weather.getHumidity(),
                        weather.getCondition(),
                        weather.getCreatedAt()
                );
    }
}
