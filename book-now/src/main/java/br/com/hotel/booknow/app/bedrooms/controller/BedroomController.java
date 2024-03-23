package br.com.hotel.booknow.app.bedrooms.controller;

import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomRequest;
import br.com.hotel.booknow.app.bedrooms.domain.dto.BedroomResponse;
import br.com.hotel.booknow.app.bedrooms.service.BedroomService;
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
@Tag(name = "Bedrooms", description = "Endpoints relacionados ao quartos")
@RestController
@RequestMapping(value = "/bedrooms")
@AllArgsConstructor
public class BedroomController {

    /**
     * Serviço para operações relacionadas a quartos.
     * <p>
     * Esse atributo é injetado pelo construtor através da <b>injeção de dependência</b> do Spring.
     */
    private final BedroomService bedroomService;

    /**
     * <b>Endpoint para cadastrar um novo quarto.</b>
     * <p>
     * Esse método recebe uma requisição <b>POST</b> no caminho <code>/register</code> e espera um objeto
     * <code>BedroomRequest</code> no corpo da requisição. O método delega a criação do quarto para o serviço
     * <code>bedroomService</code> e retorna um objeto <code>ResponseEntity</code> com o status <b>CREATED (201)</b> e
     * o objeto <code>BedroomResponse</code> recém-criado no corpo da resposta.
     *
     * @param bedroomRequest
     *         Objeto contendo os dados do quarto a ser cadastrado.
     *
     * @return ResponseEntity contendo o objeto BedroomResponse criado e o status da operação.
     */
    @PostMapping("/register")
    public ResponseEntity<BedroomResponse> createBedroom(@RequestBody BedroomRequest bedroomRequest) {
        BedroomResponse bedroomResponse = bedroomService.createBedroom(bedroomRequest);
        return new ResponseEntity<>(bedroomResponse, HttpStatus.CREATED);
    }

    /**
     * <b>Endpoint para buscar todos os quartos cadastrados.</b>
     * <p>
     * Esse método recebe uma requisição <b>GET</b> no caminho <code>/list</code> e retorna um
     * <code>ResponseEntity</code> contendo uma lista de objetos <code>BedroomResponse</code> com todos os quartos
     * cadastrados. O status da resposta é <b>OK (200)</b>.
     *
     * @return ResponseEntity contendo a lista de BedroomResponse e o status da operação.
     */
    @GetMapping("/list")
    public ResponseEntity<List<BedroomResponse>> getAllBedrooms() {
        List<BedroomResponse> bedroomResponses = bedroomService.getAllBedrooms();
        return new ResponseEntity<>(bedroomResponses, HttpStatus.OK);
    }

    /**
     * <b>Endpoint para buscar um quarto por ID.</b>
     * <p>
     * Esse método recebe uma requisição <b>GET</b> no caminho <code>/id</code> onde <code>id</code> é o identificador
     * do quarto. O ID é recuperado da anotação <code>@PathVariable(name = "id")</code>. O método delega a busca do
     * quarto para o serviço <code>bedroomService</code> e retorna um <code>ResponseEntity</code> contendo o objeto
     * <code>BedroomResponse</code> encontrado e o status <b>OK (200)</b>. Se o quarto não for encontrado, o método
     * retorna um ResponseEntity com status <b>NOT_FOUND (404)</b>.
     *
     * @param id
     *         Identificador do quarto a ser buscado.
     *
     * @return ResponseEntity contendo o BedroomResponse do quarto encontrado ou um status de erro.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BedroomResponse> getBedroomById(@PathVariable(name = "id") Long id) {
        BedroomResponse bedroomResponse = bedroomService.getBedroomById(id);
        return new ResponseEntity<>(bedroomResponse, HttpStatus.OK);
    }

    /**
     * <b>Endpoint para atualizar um quarto.</b>
     * <p>
     * Esse método recebe uma requisição PUT no caminho `/id` onde `id` é o identificador do quarto. O ID é recuperado
     * da anotação `@PathVariable(name = "id")`. O corpo da requisição deve conter um objeto `BedroomRequest` com os
     * dados atualizados do quarto. O método delega a atualização do quarto para o serviço `bedroomService` e retorna um
     * `ResponseEntity` contendo o objeto `BedroomResponse` atualizado e o status `OK` (200). Se o quarto não for
     * encontrado, o método retorna um ResponseEntity com status `NOT_FOUND` (404).
     *
     * @param id
     *         Identificador
     * @param bedroomRequest
     *
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<BedroomResponse> updateBedroom(@PathVariable(name = "id") Long id,
                                                         @RequestBody BedroomRequest bedroomRequest) {
        BedroomResponse bedroomResponse = bedroomService.updateBedroom(id, bedroomRequest);
        return new ResponseEntity<>(bedroomResponse, HttpStatus.NO_CONTENT);
    }

    /**
     * <b>xx</b>
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBedroom(@PathVariable(name = "id") Long id) {
        bedroomService.deleteBedroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
