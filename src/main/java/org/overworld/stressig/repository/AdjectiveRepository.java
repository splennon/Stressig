package org.overworld.stressig.repository;

import java.util.List;

import org.overworld.stressig.entity.Adjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjectiveRepository extends JpaRepository<Adjective, Integer> {
    
    List<Adjective> findByDeContainingIgnoreCase(String de);
    
    List<Adjective> findByEnContainingIgnoreCase(String en);
    
    List<Adjective> findByRankLessThanEqual(Integer rank);
    
    List<Adjective> findByOrderByRankAsc();
    
    @Query("SELECT a FROM Adjective a WHERE a.de = :word OR a.variations LIKE %:word%")
    List<Adjective> findByGermanWordOrVariation(@Param("word") String word);
}
