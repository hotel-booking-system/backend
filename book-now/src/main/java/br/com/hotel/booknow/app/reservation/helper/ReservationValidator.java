package br.com.hotel.booknow.app.reservation.helper;

import br.com.hotel.booknow.app.bedrooms.entity.Bedroom;
import br.com.hotel.booknow.app.bedrooms.repository.BedroomRepository;
import br.com.hotel.booknow.app.reservation.dto.ReservationRequest;
import br.com.hotel.booknow.app.reservation.entity.Reservation;
import br.com.hotel.booknow.app.reservation.entity.ReservationStatus;
import br.com.hotel.booknow.app.reservation.mapper.ReservationMapper;
import br.com.hotel.booknow.app.reservation.repository.ReservationRepository;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.RoomNotAvailableException;
import br.com.hotel.booknow.core.exceptions.errors.reservation.InvalidReservationDatesException;
import br.com.hotel.booknow.core.exceptions.errors.bedroom.RoomNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Classe responsável por validar dados de uma requisição de reserva de quarto
 *
 * @author juliane.maran
 */
@Slf4j
@Component
@AllArgsConstructor
public class ReservationValidator {

    private final ReservationRepository reservationRepository;
    private final BedroomRepository bedroomRepository;

    /**
     * <b>Valida os dados da rquisição de reserva</b>
     *
     * @param request
     *         a requisição de reserva a ser validada
     *
     * @throws InvalidReservationDatesException
     *         se a data de entrada for posterior à data de saída
     * @throws RoomNotAvailableException
     *         se o quarto não estiver disponível para as datas selecionadas
     */
    public void validate(ReservationRequest request) {
        log.info("Validando dados da reserva.");
        validateDates(request);
        validateRoomAvailability(request);
        log.info("Validação da reserva concluída.");
    }

    /**
     * <b>Encontra o quarto pelo seu ID</b>
     *
     * @param rommId
     *         Identificador único do quarto
     *
     * @return O quarto encontrado
     *
     * @throws RoomNotFoundException
     *         Se o quarto não for encontrado
     */
    public Bedroom findRoomById(Long rommId) {
        return bedroomRepository.findById(rommId)
                .orElseThrow(RoomNotFoundException::new);
    }

    /**
     * <b>Cria uma entidade de reserva</b>
     *
     * @param bedroom
     *         O quarto da reserva
     * @param request
     *         A requisição da reserva
     *
     * @return A entidade de reserva criada
     */
    public Reservation createReservationEntity(Bedroom bedroom, ReservationRequest request) {
        BigDecimal totalAmount = calculateTotalAmount(bedroom.getDailyRate(),
                request.getCheckinDate(), request.getCheckoutDate());
        Reservation reservation = ReservationMapper.INSTANCE.toEntity(request);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setTotalAmount(totalAmount);
        reservation.setRoomId(bedroom.getBedroomId());
        return reservation;
    }

    /**
     * <b>Valida se o quarto está disponível para as datas seleciondas</b>
     *
     * @param request
     *         a requisição de reserva a ser validada
     *
     * @throws RoomNotAvailableException
     *         Se o quarto não estiver disponível para as datas selecionadas
     */
    private void validateRoomAvailability(ReservationRequest request) {
        log.info("Verificando disponibilidade do quarto.");
        Bedroom bedroom = findRoomById(request.getRoomId());
        if (!isRoomAvailable(bedroom, request.getCheckinDate(), request.getCheckoutDate())) {
            log.error("Quarto indisponível para as datas solicitadas.");
            throw new RoomNotAvailableException("Quarto indisponível para as datas selecionadas.");
        }
        log.info("Quarto disponível para as datas selecionadas!");
    }

    /**
     * <b>Valida se a data de entrada é posterior à data de saída</b>
     *
     * @param request
     *         A requisição de reserva a ser validada
     *
     * @throws InvalidReservationDatesException
     *         Se a data de entrada for posterior à data de saída
     */
    private void validateDates(ReservationRequest request) {
        log.info("Validando datas de entrada e saída!");
        if (request.getCheckinDate().isAfter(request.getCheckoutDate())) {
            log.error("Data de entrada inválida: não pode ser posterior à data de saída.");
            throw new InvalidReservationDatesException("Data de entrada não pode ser posterior à data de saída!");
        }
        log.info("datas validadas com sucesso!");
    }

    /**
     * <b>Verifica se um quarto está disponível para as datas especificadas</b>
     *
     * @param bedroom
     *         O quarto a ser verificado
     * @param checkinDate
     *         A data de entrada da reserva
     * @param checkoutDate
     *         A data de saída da reserva
     *
     * @return True se o quarto está disponível, false coso esteja indisponível
     */
    private boolean isRoomAvailable(Bedroom bedroom, LocalDate checkinDate, LocalDate checkoutDate) {
        List<Reservation> reservations = reservationRepository
                .findByRoomIdAndCheckinDateAndCheckoutDate(bedroom.getBedroomId(), checkinDate, checkoutDate);
        for (Reservation reservation : reservations) {
            if (isOverlapping(reservation.getCheckinDate(), reservation.getCheckoutDate(), checkinDate, checkoutDate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>Verifica se dois intervalos de datas se sobrepõem</b>
     *
     * @param startDate1
     *         Data de início do primeiro intervalo
     * @param endDate1
     *         Data de fim do primeiro intervalo
     * @param startDate2
     *         Data de início do segundo intervalo
     * @param endDate2
     *         Data de fim do segundo intervalo
     *
     * @return True se os intervalos se sobrepõem, false caso contrário
     */
    private boolean isOverlapping(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        return !(startDate1.isAfter(endDate2) || endDate1.isBefore(startDate2));
    }

    /**
     * <b>Calcula o valor total de uma reserva</b>
     *
     * @param dailyRate
     *         A diára do quarto
     * @param checkinDate
     *         A data de entrada da reserva
     * @param checkoutDate
     *         A data de saída da reserva
     *
     * @return O valor total da reserva
     */
    private BigDecimal calculateTotalAmount(BigDecimal dailyRate, LocalDate checkinDate, LocalDate checkoutDate) {
        long daysBetween = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        return dailyRate.multiply(BigDecimal.valueOf(daysBetween));
    }

}
