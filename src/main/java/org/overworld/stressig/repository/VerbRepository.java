package org.overworld.stressig.repository;

import org.overworld.stressig.entity.Verb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Integer> {
    
    List<Verb> findByDeContainingIgnoreCase(String de);
    
    List<Verb> findByEnContainingIgnoreCase(String en);
    
    List<Verb> findByRankLessThanEqual(Integer rank);
    
    List<Verb> findByOrderByRankAsc();
    
    List<Verb> findByHilfsverb(String hilfsverb);
    
    @Query("SELECT v FROM Verb v WHERE v.de = :word OR " +
          "v.praesensIch = :word OR v.praesensDu = :word OR v.praesensErSieEs = :word OR " +
          "v.praeteritumIch = :word OR v.partizipII = :word OR " +
          "v.konjunktivIIIch = :word OR v.imperativSingular = :word OR v.imperativPlural = :word")
    List<Verb> findByAnyConjugatedForm(@Param("word") String word);
    
    @Query("SELECT v FROM Verb v WHERE v.praesensIch IS NOT NULL AND v.praesensDu IS NOT NULL")
    List<Verb> findVerbsWithPresentConjugation();
    
    @Query("SELECT v FROM Verb v WHERE v.partizipII IS NOT NULL")
    List<Verb> findVerbsWithPartizipII();
}
