package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.repository.SearchHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchHistoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistoryDTO> getAllSearchHistory() {
        return searchHistoryRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<SearchHistoryDTO> findAllByUserMail(String userMail) {
        return searchHistoryRepository.findAllByUserMail(userMail)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public SearchHistoryDTO convertEntityToDto(SearchHistory searchHistory) {
        var searchHistoryDTO = new SearchHistoryDTO();
        searchHistoryDTO = modelMapper.map(searchHistory, SearchHistoryDTO.class);
        return searchHistoryDTO;

    }
}
