package br.com.hotel.booknow.app.reservation.dto;

import br.com.hotel.booknow.app.reservation.entity.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

//    @JsonProperty(namespace = "check_in_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkinDate;

//    @JsonProperty(namespace = "check_out_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkoutDate;

//    @JsonProperty(namespace = "number_adults")
    private Integer numberAdults;

//    @JsonProperty(namespace = "number_children")
    private Integer numberChildren;

//    @JsonProperty(namespace = "total_amount")
    private BigDecimal totalAmount;

//    @JsonProperty(namespace = "reservation_status")
    private ReservationStatus reservationStatus;

//    @JsonProperty(namespace = "room_id")
    private Long roomId;

}
