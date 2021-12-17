package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    // Testing query with user_mail = 'chai1'
    @Query(value = "SELECT * FROM SEARCH_HISTORY WHERE user_mail='chai1'",
            nativeQuery = true)
    Collection<SearchHistory> getSearchHistoriesByUserMail(String userMail);
}
