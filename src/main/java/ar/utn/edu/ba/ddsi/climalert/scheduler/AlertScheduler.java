package ar.utn.edu.ba.ddsi.climalert.scheduler;


import ar.utn.edu.ba.ddsi.climalert.services.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertScheduler {
    private final AlertService alertService;

    public AlertScheduler(AlertService alertService) {
        this.alertService = alertService;
    }

    @Scheduled(fixedRate = 60000)
    public void processAlerts() {
        alertService.processLastWeatherRecord();
    }
}
