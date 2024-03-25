package br.com.hotel.booknow.app.reservation.controller;

import br.com.hotel.booknow.app.reservation.dto.ReservationRequest;
import br.com.hotel.booknow.app.reservation.dto.ReservationResponse;
import br.com.hotel.booknow.app.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "API Booknow",
                description = "API REST Booknow - Reservation",
                version = "1.0.0"
        ),
        tags = @Tag(
                name = "users",
                description = "Gerenciamento de reservas"
        ),
        servers = @Server(
                url = "http://localhost:8585/api/v1/reservations"
        )
)
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
