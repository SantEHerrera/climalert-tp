package ar.utn.edu.ba.ddsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(
        @JsonProperty("temp_c")
        Double temperature,

        Integer humidity,

        Condition condition
) {}
