package com.tenniscourts.tenniscourts;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.schedules.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisCourtService {

    private final TennisCourtRepository tennisCourtRepository;
    private final ScheduleService scheduleService;
    private final TennisCourtMapper tennisCourtMapper;

    public TennisCourtDTO addTennisCourt(TennisCourtDTO tennisCourt) {
        return tennisCourtMapper.map(tennisCourtRepository.saveAndFlush(tennisCourtMapper.map(tennisCourt)));
    }

    public TennisCourtDTO findTennisCourtById(Long id) {
        return tennisCourtRepository.findById(id).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    }

    public TennisCourtDTO findTennisCourtWithSchedulesById(Long tennisCourtId) {
        TennisCourtDTO tennisCourtDTO = findTennisCourtById(tennisCourtId);
        tennisCourtDTO.setTennisCourtSchedules(scheduleService.findSchedulesByTennisCourtId(tennisCourtId));
        return tennisCourtDTO;
    }

    public TennisCourtDTO findTennisCourtByName(final String name) {
        return tennisCourtRepository.findByName(name).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    }

    public void deleteTennisCourtById(final Long id) {
        final TennisCourt tennisCourt = tennisCourtRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
        tennisCourtRepository.delete(tennisCourt);
    }

    public void updateTennisCourt(final TennisCourtDTO tennisCourtDTO) {
        final TennisCourt tennisCourt = tennisCourtRepository.findById(tennisCourtDTO.getId()).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court does not exist in DB!. Please try to create it first using the create tennis court API.");
        });
        tennisCourt.setName(tennisCourtDTO.getName());
        tennisCourtRepository.saveAndFlush(tennisCourt);
    }
}
