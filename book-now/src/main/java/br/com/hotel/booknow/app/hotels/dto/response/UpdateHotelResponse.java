package br.com.hotel.booknow.app.hotels.dto.response;

import br.com.hotel.booknow.app.hotels.entity.HotelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // retorna apenas propriedades NÃO nulas
public class UpdateHotelResponse {

    private Long id;
    private String hotelName;
    private String location;
    private String phoneNumber;
    private String email;
    private HotelType hotelType;
    private String cnpjNumber;
    private String description;
    private Integer roomCount;
    private LocalDateTime updatedAt;

}
