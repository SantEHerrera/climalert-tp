package ar.utn.edu.ba.ddsi.climalert.repositories;

import ar.utn.edu.ba.ddsi.climalert.entities.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {

    Optional<WeatherRecord> findFirstByOrderByCreatedAtDesc();

}
