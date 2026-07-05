package ar.utn.edu.ba.ddsi.climalert.cliente;

import ar.utn.edu.ba.ddsi.climalert.dtos.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherApiClient {
    private final RestClient restClient;

    @Value("${weather.api-key}")
    private String API_KEY;

    public WeatherApiClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.weatherapi.com/v1").build();
    }

    public WeatherApiResponse getCurrentWeather() {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", API_KEY)
                        .queryParam("q", "CABA")
                        .build())
                .retrieve()
                .body(WeatherApiResponse.class);
    }
}
