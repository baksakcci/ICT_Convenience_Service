package com.example.sinabro.service;

import com.example.sinabro.dto.warning.WarningRequestDto;
import com.example.sinabro.dto.warning.WarningResponseDto;
import com.example.sinabro.entity.warnning.Warning;
import com.example.sinabro.exception.WarningNotFoundException;
import com.example.sinabro.repository.WarningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarningService {

    private final WarningRepository warningRepository;

    @Transactional
    public void createWarning(WarningRequestDto wrd) {
        Warning warning = new Warning(wrd.getWarning(), wrd.getMessage());
        warningRepository.save(warning);
    }

    @Transactional
    public void updateWarning(WarningRequestDto wrd, Long id) {
        Warning warning = warningRepository.findById(id).orElseThrow(WarningNotFoundException::new);
        warning.editWarning(warning.getWarning(), warning.getMessage());
        warningRepository.save(warning);
    }

    @Transactional
    public void deleteWarning(Long id) {
        Warning warning = warningRepository.findById(id).orElseThrow(WarningNotFoundException::new);
        warningRepository.delete(warning);
    }

    @Transactional(readOnly = true)
    public WarningResponseDto findWarning(Long id) {
        Warning warning = warningRepository.findById(id).orElseThrow(WarningNotFoundException::new);
        return WarningResponseDto.toDto(warning);
    }

    @Transactional(readOnly = true)
    public List<WarningResponseDto> findWarningAll() {
        List<Warning> warnings = warningRepository.findAll();
        List<WarningResponseDto> result = new ArrayList<>();
        for (Warning w : warnings) {
            result.add(WarningResponseDto.toDto(w));
        }
        return result;
    }
}
