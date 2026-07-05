package ar.utn.edu.ba.ddsi.climalert.dtos;


public record WeatherApiResponse(
        Location location,
        Current current
) {}
