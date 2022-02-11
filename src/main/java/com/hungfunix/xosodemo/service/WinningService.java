package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Winning;
import com.hungfunix.xosodemo.repository.WinningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinningService {

    @Autowired
    WinningRepository winningRepository;

    public List<Winning> getWinnings() {
        return winningRepository.findAll();
    }

}

