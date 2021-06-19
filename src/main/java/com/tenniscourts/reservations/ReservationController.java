package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/reservation")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<Void> bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PostMapping("/reschedule/{reservationId}/{scheduleId}")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable Long reservationId,
                                                                @PathVariable Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
