package br.com.hotel.booknow.app.hotels.controller;

import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import br.com.hotel.booknow.app.hotels.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author juliane.maran
 * @since 19-03-2024
 */
@RestController
@RequestMapping(value = "/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    /**
     * <b>Cria um novo hotel.</b>
     *
     * @param hotel
     *         Objeto Hotel contendo os dados do hotel a ser criado.
     *
     * @return recém-criado persistido no banco de dados.
     */
    @PostMapping("/create")
    public ResponseEntity<Hotel> registerHotel(@RequestBody Hotel hotel) {
        Hotel response = hotelService.createHotel(hotel);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * <b>Busca um hotel por ID.</b>
     *
     * @param hotelId
     *         Identificador único do hotel.
     *
     * @return Hotel encontrado, ou lança uma exceção caso não seja encontrado.
     */
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable(name = "hotelId") Long hotelId) {
        Hotel hotel = hotelService.findHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

    /**
     * <b>Lista todos os hotéis cadastrados.</b>
     *
     * @return Lista de hotéis encontrados.
     */
    @GetMapping("/list")
    public ResponseEntity<List<Hotel>> listHotels() {
        List<Hotel> hoteis = hotelService.listHotels();
        return ResponseEntity.ok(hoteis);
    }

    /**
     * <b>Atualiza os dados de um hotel existente.</b>
     *
     * @param hotelsId
     *         Identificador único do hotel a ser atualizado.
     * @param hotel
     *         Objeto Hotel contendo os novos dados do hotel.
     *
     * @return Hotel atualizado persistido no banco de dados.
     */
    @PutMapping("/{hotelsId}")
    public ResponseEntity<Hotel> updateHotelById(@PathVariable(name = "hotelsId") Long hotelsId,
                                                 @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(hotelsId, hotel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedHotel);
    }

    /**
     * <b>Exclui um hotel por ID.</b>
     *
     * @param hotelsId
     *         Identificador único do hotel.
     */
    @DeleteMapping("/{hotelsId}")
    public ResponseEntity<String> deleteHotelById(@PathVariable(name = "hotelsId") Long hotelsId) {
        hotelService.deleteHotel(hotelsId);
        return ResponseEntity.ok("Hotel with ID " + hotelsId + " deleted successfully.");
    }

    /**
     * <b>Busca hotéis de acordo com critérios de pesquisa.</b>
     *
     * @param name
     *         Nome do hotel (opcional). Pesquisa por hotéis que contenham o nome informado.
     * @param cnpj
     *         CNPJ do hotel (opcional). Pesquisa por hotéis com o CNPJ exato informado.
     * @param email
     *         Email do hotel (opcional). Pesquisa por hotéis com o e-mail exato informado.
     * @param type
     *         Tipo de hotel (opcional). Pesquisa por hotéis do tipo informado.
     *
     * @return Lista de hotéis que atendem aos critérios de pesquisa.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotel(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "cnpj") String cnpj,
            @RequestParam(required = false, name = "email") String email,
            @RequestParam(required = false, name = "type") HotelType type) {

        List<Hotel> response = hotelService.searchHotel(name, cnpj, email, type);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
