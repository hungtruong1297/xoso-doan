package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.ResultRepository;
import com.hungfunix.xosodemo.repository.SearchHistoryRepository;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class SearchHistoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    JwtUtil jwtUtil;

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

    public ResponseEntity<?> addSearchHistory(SearchHistoryDTO searchHistoryDTO) throws ParseException {
//        try {
            var searchHistory = convertDtoToEntity(searchHistoryDTO);

            // V1:
            searchHistoryRepository.save(searchHistory);
            MessageResponse message = new MessageResponse("Chúc bạn may mắn lần sau!");

        SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

        String dateStr = searchHistoryDTO.getDate().toString(); // Output: "Sun Nov 28 00:00:00 ICT 2021";
        long provinceId = searchHistoryDTO.getProvinceId();
        Date date = inputFormat.parse(dateStr);

        List<Result> results = resultRepository.findByProvinceIdAndDate(provinceId, date);
        results.forEach(result -> {
            if (searchHistoryDTO.getSearchValue().endsWith(result.getResult())) {
                message.setMessage("Chúc mừng bạn! Giải: " + result.getWinning().getName());
            }
        });
            return ResponseEntity.ok().body(message);

//        }
//        catch (Exception e) {
//            return ResponseEntity.status(500).body(new MessageResponse("Unknown Error :D"));
//        }
    }

    // Demo Mapper
    // Source: https://www.baeldung.com/java-dto-pattern
    public SearchHistoryDTO convertEntityToDto(SearchHistory searchHistory) {
        var searchHistoryDTO = new SearchHistoryDTO();
        searchHistoryDTO = modelMapper.map(searchHistory, SearchHistoryDTO.class);
        return searchHistoryDTO;

    }

    public SearchHistory convertDtoToEntity(SearchHistoryDTO searchHistoryDTO) {
        var searchHistory = modelMapper.map(searchHistoryDTO, SearchHistory.class);
        // TODO: Is this a good practice, when Service layer call Repo layer (user), and to only get userId
        User user = userRepository.findUserByMail(searchHistoryDTO.getUserMail());
        searchHistory.setUser(user);
        return searchHistory;
    }

    public void deleteById(Long id) {
        searchHistoryRepository.deleteById(id);
    }
}
