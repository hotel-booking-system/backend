package br.com.hotel.booknow.app.bedrooms.controller;

import br.com.hotel.booknow.app.bedrooms.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.service.BedroomService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>Classe controladora para endpoints relacionados a quartos.</b>
 * <p>
 * Essa classe é anotada com <code>@RestController</code> para indicar que ela é um controlador Spring MVC e todas as
 * suas rotas retornam objetos JSON. A anotação <code>@RequestMapping(value = "/bedrooms")</code> define o caminho base
 * para todas as rotas mapeadas nessa classe, que é <code>/bedrooms</code>.
 * <p>
 * A classe também é anotada com <code>@AllArgsConstructor</code>, que gera automaticamente um construtor com argumentos
 * para todos os atributos privados da classe.
 */
@RestController
@RequestMapping(value = "/bedrooms")
@AllArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "API Booknow",
                description = "API REST Booknow - Bedrooms",
                version = "1.0.0"
        ),
        tags = @Tag(
                name = "users",
                description = "Gerenciamento de quartos"
        ),
        servers = @Server(
                url = "http://localhost:8585/api/v1/bedrooms"
        )
)
public class BedroomController {

    /**
     * Serviço para operações relacionadas a quartos.
     * <p>
     * Esse atributo é injetado pelo construtor através da <b>injeção de dependência</b> do Spring.
     */
    private final BedroomService bedroomService;

    /**
     * <b>Cadastrar novo quarto</b>
     *
     * @param request
     *         Objeto contendo os dados do quarto a ser cadastrado.
     *
     * @return ResponseEntity contendo o objeto BedroomResponse criado e o status da operação.
     */
    @PostMapping("/register")
    public ResponseEntity<BedroomResponse> registerBedroom(@RequestBody BedroomRequest request) {
        BedroomResponse response = bedroomService.createBedroom(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * <b>Lista todos os quartos</b>
     *
     * @return Retorna uma lista de todos os quartos cadastrados no sistema
     */
    @GetMapping("/list")
    public ResponseEntity<List<BedroomResponse>> listBedrooms() {
        List<BedroomResponse> responseList = bedroomService.getAllBedrooms();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    /**
     * <b>Busca um quarto por ID</b>
     *
     * @param id
     *         Identificador do quarto a ser buscado.
     *
     * @return Retorna um quarto específico com base no ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<BedroomResponse> getBedroomById(@PathVariable(name = "id") Long id) {
        BedroomResponse response = bedroomService.getBedroomById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * <b>Atualizar um quarto por ID</b>
     *
     * @param id
     *         Identificador único do quarto (Unique bedroom identifier)
     * @param request
     *         Objeto contendo os dados do quarto a ser cadastrado.
     *
     * @return Atualiza um quarto específico com base no ID fornecido e nos dados enviados no campo da requisição.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BedroomResponse> updateBedroomById(@PathVariable(name = "id") Long id,
                                                             @RequestBody BedroomRequest request) {
        BedroomResponse response = bedroomService.updateBedroom(id, request);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /**
     * <b>Excluir um quarto por ID</b>
     *
     * @param id
     *         Identificador único do quarto (Unique bedroom identifier)
     *
     * @return Exclui um quarto específico com base no ID fornecidos
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBedroomById(@PathVariable(name = "id") Long id) {
        bedroomService.deleteBedroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{hotelId}/hotels")
    public ResponseEntity<List<BedroomResponse>> getBedroomsByHotelId(@PathVariable Long hotelId) {
        List<BedroomResponse> response = bedroomService.getAllBedroomByHotelId(hotelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
