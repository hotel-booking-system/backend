package br.com.hotel.booknow.app.hotels.service;

import br.com.hotel.booknow.app.bedrooms.domain.mapper.BedroomMapper;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.app.hotels.domain.entity.Hotel;
import br.com.hotel.booknow.app.hotels.domain.entity.HotelType;
import br.com.hotel.booknow.app.hotels.domain.mapper.HotelMapper;
import br.com.hotel.booknow.app.hotels.repository.HotelRepository;
import br.com.hotel.booknow.app.hotels.validator.HotelValidator;
import br.com.hotel.booknow.core.exceptions.errors.BadRequestException;
import br.com.hotel.booknow.core.exceptions.errors.ConflictException;
import br.com.hotel.booknow.core.exceptions.errors.NotFoundException;
import br.com.hotel.booknow.core.exceptions.errors.ServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author juliane.maran
 * @since 19-03-2024
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final BedroomMapper bedroomMapper;

    private final BedroomRepository bedroomRepository;

    /**
     * Repositório para acesso aos dados do Hotel.
     */
    private final HotelRepository hotelRepository;
    /**
     * Validador de dados de Hotéis.
     */
    private final HotelValidator hotelValidator;

    private final HotelMapper hotelMapper;

    /**
     * <b>Cria um novo Hotel.</b>
     *
     * @param hotel
     *         Objeto Hotel contendo os dados do hotel a ser criado.
     *
     * @return O hotel recém-criado.
     *
     * @throws BadRequestException
     *         Se o hotel for inválido ou já existir um hotel com o mesmo CNPJ.
     * @throws ConflictException
     *         Se já existir um hotel com o mesmo CNPJ ou mesmo Nome ou mesmo Email.
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor.
     */
    @Transactional
    public Hotel createHotel(Hotel hotel) {
        try {
            hotel.setCreatedAt(LocalDateTime.now());
            if (hotelRepository.existsByCnpjNumber(hotel.getCnpjNumber())) {
                throw new IllegalArgumentException("CNPJ já cadastrado para outro hotel");
            }
            if (hotelRepository.existsByEmail(hotel.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado para outro hotel");
            }
            return hotelRepository.save(hotel);
        } catch (BadRequestException e) {
            log.error("Erro ao cadastrar hotel: {}", hotel);
            throw new BadRequestException();
        } catch (ConflictException e) {
            log.error("Hotel já existe com os dados informados.");
            throw new ConflictException();
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao cadastrar Hotel: {}", e.getMessage());
            throw new ServerErrorException();
        }
    }

    /**
     * <b>Busca um hotel pelo seu identificador.</b>
     *
     * @param id
     *         Identificador do hotel.
     *
     * @return O hotel encontrado, ou null caso não seja encontrado.
     *
     * @throws BadRequestException
     *         Se o identificador for inválido (não numérico).
     * @throws NotFoundException
     *         Se o hotel não for encontrado.
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor.
     */
    @Transactional(readOnly = true)
    public Hotel findHotelById(Long id) {
        try {
            return hotelValidator.getExistingHotel(id);
        } catch (BadRequestException e) {
            log.error("O ID do hotel deve ser um número inteiro. Valor informado: {}", id);
            throw new BadRequestException();
        } catch (NotFoundException e) {
            log.error("Hotel não encontrado com o ID: {}", id);
            throw new NotFoundException();
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao buscar Hotel: {}", e.getMessage());
            throw new ServerErrorException();
        }
    }

    /**
     * <b>Lista todos os hotéis cadastrados.</b>
     *
     * @return Uma lista contendo todos os hotéis cadastrados.
     *
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor.
     */
    @Transactional(readOnly = true)
    public List<Hotel> listHotels() {
        try {
            return hotelRepository.findAll();
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao listar hotéis: {}", e.getMessage());
            throw new ServerErrorException();
        }
    }

    /**
     * <b>Exclui um hotel.</b>
     *
     * @param id
     *         Identificador do hotel a ser excluído.
     *
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor.
     */
    @Transactional
    public void deleteHotel(Long id) {
        try {
            Hotel hotel = findHotelById(id);
            hotelRepository.delete(hotel);
            log.info("Hotel com ID {} excluído com sucesso.", id);
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao excluir Hotel: {}", e.getMessage());
            throw new ServerErrorException();
        }
    }

    /**
     * <b>Atualiza os dados de um hotel.</b>
     *
     * @param hotelId
     *         Identificador do hotel a ser atualizado.
     * @param hotel
     *         Objeto Hotel contendo os dados atualizados.
     *
     * @return O hotel atualizado.
     *
     * @throws BadRequestException
     *         Se o hotel for inválido ou os dados de atualização forem inconsistentes.
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor.
     */
    @Transactional
    public Hotel updateHotel(Long hotelId, Hotel hotel) {
        try {
            Hotel existingHotel = hotelValidator.getExistingHotel(hotelId);
            hotelValidator.verifyUpdatedFields(hotel, existingHotel);
            existingHotel.setUpdatedAt(LocalDateTime.now());
            log.info("Atualizando hotel com ID {}: {}", hotelId, hotel);
            return hotelRepository.save(existingHotel);
        } catch (BadRequestException e) {
            log.error("Erro ao atualizar Hotel com ID {}", hotelId);
            throw new BadRequestException();
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao atualizar dados do Hotel: {}", e.getMessage());
            throw new ServerErrorException();
        }
    }

    /**
     * <b>Busca hotéis de acordo com critérios de pesquisa.</b>
     *
     * @param nome
     *         Nome do hotel (opcional). Pesquisa por hotéis que contenham o nome informado.
     * @param cnpj
     *         CNPJ do hotel (opcional). Pesquisa por hotéis com o CNPJ exato informado.
     * @param email
     *         E-mail do hotel (opcional). Pesquisa por hotéis com o e-mail exato informado.
     * @param hotelType
     *         Tipo de hotel (opcional). Pesquisa por hotéis do tipo informado.
     *
     * @return Uma lista contendo os hotéis que atendem aos critérios de pesquisa.
     *
     * @throws BadRequestException
     *         Se a requisição de busca for inválida.
     * @throws NotFoundException
     *         Se nenhum hotel for encontrado de acordo com os critérios de pesquisa.
     * @throws ServerErrorException
     *         Se ocorrer um erro inesperado no servidor durante a busca.
     */
    @Transactional(readOnly = true)
    public List<Hotel> searchHotel(String nome, String cnpj, String email, HotelType type) {

        List<Hotel> results = new ArrayList<>();

        try {

            if (StringUtils.hasText(nome)) {
                results.addAll(hotelValidator.findByName(nome));
            }

            if (StringUtils.hasText(cnpj)) {
                results.addAll(hotelValidator.findByCnpj(cnpj));
            }

            if (StringUtils.hasText(email)) {
                results.addAll(hotelValidator.findByEmail(email));
            }

            if (type != null) {
                results.addAll(hotelValidator.findHotelType(type));
            }

            if (results.isEmpty()) {
                log.info("Nenhum hotel encontrado com os critérios de pesquisa.");
                throw new NotFoundException();
            }

            return results;

        } catch (BadRequestException e) {
            log.error("Erro na requisição de busca de hotel: {}", e.getMessage());
            throw new BadRequestException();
        } catch (NotFoundException e) {
            log.error("Nenhum hotel encontrado com os critérios de pesquisa.");
            throw new NotFoundException();
        } catch (ServerErrorException e) {
            log.error("Erro inesperado ao buscar Hotel: {}", e.getMessage());
            throw new ServerErrorException();
        }

    }

//    public HotelResponse listHotel(Long id) {
//        Hotel hotel = hotelRepository.findById(id).orElseThrow(NotFoundException::new);
//        List<Bedroom> bedrooms = bedroomRepository.findAllByHotelId(hotel.getId());
//        HotelResponse response = hotelMapper.toHotelResponse(hotel);
//        response.setBedrooms(bedrooms.stream().map(bedroomMapper::toBedroomResponse)
//                .collect(Collectors.toList()));
//        return response;
//    }

}