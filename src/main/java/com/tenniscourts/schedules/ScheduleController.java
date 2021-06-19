package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Api(value = "ScheduleController", description = "Rest API related to scheduling!")
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/scheduling")
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "Add schedule tennis court!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/add")
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Find schedules by dates!", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @GetMapping("/find")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam LocalDate startDate,
                                                                  @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @ApiOperation(value = "Find schedule by id!", response = ScheduleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Schedule not found")})
    @GetMapping("/find/{id}")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
