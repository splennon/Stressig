package org.overworld.stressig.repository;

import org.overworld.stressig.entity.Adverb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdverbRepository extends JpaRepository<Adverb, Integer> {
    
    List<Adverb> findByDeContainingIgnoreCase(String de);
    
    List<Adverb> findByEnContainingIgnoreCase(String en);
    
    List<Adverb> findByRankLessThanEqual(Integer rank);
    
    List<Adverb> findByOrderByRankAsc();
    
    @Query("SELECT a FROM Adverb a WHERE a.de = :word OR a.variations LIKE %:word%")
    List<Adverb> findByGermanWordOrVariation(@Param("word") String word);
}
