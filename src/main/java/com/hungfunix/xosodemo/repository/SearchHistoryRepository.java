package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.dto.projection.SearchHistoryView;
import com.hungfunix.xosodemo.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    // Testing query with user_mail y= 'chai1'
//    @Query(value = "SELECT * FROM SEARCH_HISTORY WHERE user_mail='chai1'",
//            nativeQuery = true)
//    Collection<SearchHistory> getSearchHistoriesByUserMail(String userMail);

    @Query( nativeQuery = true,
            value = "SELECT * " +
                    "FROM SEARCH_HISTORY AS S " +
                    "JOIN [USER] AS U ON S.user_id = U.user_id " +
                    "JOIN PROVINCES AS P ON S.province_id = P.id " +
                    "WHERE U.user_mail = :userMail")
    List<SearchHistory> findAllByUserMail(@Param("userMail") String userMail);

    // Demo Projection: Must have at least 1 parameter
    // Using for limit the results return
    Collection<SearchHistoryView> findBySearchValue(String searchValue);



}
