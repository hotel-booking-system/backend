package br.com.hotel.booknow.app.hotels.controller;

import br.com.hotel.booknow.app.hotels.domain.dto.request.HotelRequest;
import br.com.hotel.booknow.app.hotels.domain.dto.response.HotelResponse;
import br.com.hotel.booknow.app.hotels.service.HotelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>Classe controladora para endpoints relacionados a Hotéis</b>
 *
 * @author juliane.maran
 * @since 19-03-2024
 */
@Tag(name = "hotel", description = "Operações relacionadas a hotéis")
@RestController
@RequestMapping(value = "/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/register")
    public ResponseEntity<HotelResponse> registerHotel(@RequestBody HotelRequest request) {
        HotelResponse hotel = hotelService.createHotel(request);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HotelResponse>> listHotel() {
        List<HotelResponse> responseList = hotelService.getAllHotels();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable(name = "id") Long id) {
        HotelResponse response = hotelService.getHotelById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotelById(@PathVariable(name = "id") Long id,
                                                         @RequestBody HotelRequest request) {
        HotelResponse response = hotelService.updateHotel(id, request);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable(name = "id") Long id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
