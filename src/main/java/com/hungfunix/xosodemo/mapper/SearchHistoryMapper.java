package com.hungfunix.xosodemo.mapper;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.model.SearchHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SearchHistoryMapper {
    SearchHistoryDTO toSearchHistoryDTO(SearchHistory searchHistory);

    List<SearchHistoryDTO> toSearchHistoryDTOs(List<SearchHistory> searchHistories);

    SearchHistory toSearchHistory(SearchHistoryDTO searchHistoryDTO);
}
