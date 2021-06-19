package com.tenniscourts.reservations;

import com.tenniscourts.schedules.Schedule;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository repository;

    @Mock
    private ReservationMapper mapper;

    @InjectMocks
    private ReservationService sut;

    @Test
    public void getRefundValueFullRefund() {
        //arrange
        Schedule schedule = new Schedule();
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);
        schedule.setStartDateTime(startDateTime);

        //act
        final BigDecimal actual = sut.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build());

        //confirmare/assert
        Assertions.assertThat(actual).isEqualTo(BigDecimal.valueOf(10));
    }
    @Test
    public void getRefundValue_ZeroRefund() {
        Schedule schedule = new Schedule ();
        LocalDateTime startDateTime = LocalDateTime.now().plusHours(2);
        schedule.setStartDateTime((startDateTime));

        final BigDecimal actual = sut.getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(3L)).build());

        Assertions.assertThat(actual).isEqualTo(BigDecimal.ZERO);


    }

}