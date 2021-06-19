package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "TennisCourtController", description = "Rest API related to tennis court!")
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/court")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @ApiOperation(value = "Save new tennis court in repo!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PostMapping("/add")
    public ResponseEntity<Void> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @ApiOperation(value = "Find tennis court by id!", response = TennisCourtDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "tennis court not found")})
    @GetMapping("/find/{id}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @ApiOperation(value = "Find tennis court with schedules by id!", response = TennisCourtDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "tennis court not found")})
    @GetMapping("/find/schedules/{id}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }

    @ApiOperation(value = "Find tennis court with schedules by name!", response = TennisCourtDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "tennis court not found")})
    @GetMapping("/byName")
    public ResponseEntity<TennisCourtDTO> findTennisCourtByName(final String name) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtByName(name));
    }

    @ApiOperation(value = "Delete tennis court!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @DeleteMapping("/deleteById/{id}")
    public void deleteTennisCourt(@PathVariable final Long courtId) {
        tennisCourtService.deleteTennisCourtById(courtId);
    }

    @ApiOperation(value = "Update tennis court in repo!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    @PutMapping("/update")
    public void updateTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        tennisCourtService.updateTennisCourt(tennisCourtDTO);
    }
}
