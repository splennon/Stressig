package org.overworld.stressig.repository;

import org.overworld.stressig.entity.Noun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NounRepository extends JpaRepository<Noun, Integer> {
    
    List<Noun> findByDeContainingIgnoreCase(String de);
    
    List<Noun> findByEnContainingIgnoreCase(String en);
    
    List<Noun> findByRootContainingIgnoreCase(String root);
    
    List<Noun> findByArticle(String article);
    
    List<Noun> findByRankLessThanEqual(Integer rank);
    
    List<Noun> findByOrderByRankAsc();
    
    @Query("SELECT n FROM Noun n WHERE n.article IN ('der', 'der/die', 'der/das')")
    List<Noun> findMasculineNouns();
    
    @Query("SELECT n FROM Noun n WHERE n.article IN ('die', 'der/die', 'die/das')")
    List<Noun> findFeminineNouns();
    
    @Query("SELECT n FROM Noun n WHERE n.article IN ('das', 'der/das', 'die/das')")
    List<Noun> findNeuterNouns();
    
    @Query("SELECT n FROM Noun n WHERE n.de = :word OR n.plural = :word")
    List<Noun> findByGermanWordOrPlural(@Param("word") String word);
}
