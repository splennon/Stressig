package org.overworld.stressig.controller;

import java.util.List;
import java.util.Optional;

import org.overworld.stressig.entity.Adjective;
import org.overworld.stressig.repository.AdjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adjectives")
@CrossOrigin(origins = "*")
public class AdjectiveController {
    
    @Autowired
    private AdjectiveRepository adjectiveRepository;
    
    @GetMapping
    public List<Adjective> getAllAdjectives() {
        return adjectiveRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Adjective> getAdjectiveById(@PathVariable Integer id) {
        Optional<Adjective> adjective = adjectiveRepository.findById(id);
        return adjective.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search/german")
    public List<Adjective> searchByGerman(@RequestParam String word) {
        return adjectiveRepository.findByDeContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/english")
    public List<Adjective> searchByEnglish(@RequestParam String word) {
        return adjectiveRepository.findByEnContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/variation")
    public List<Adjective> searchByVariation(@RequestParam String word) {
        return adjectiveRepository.findByGermanWordOrVariation(word);
    }
    
    @GetMapping("/rank/{maxRank}")
    public List<Adjective> getAdjectivesByRank(@PathVariable Integer maxRank) {
        return adjectiveRepository.findByRankLessThanEqual(maxRank);
    }
    
    @GetMapping("/ordered")
    public List<Adjective> getAdjectivesOrderedByRank() {
        return adjectiveRepository.findByOrderByRankAsc();
    }
    
    @PostMapping
    public Adjective createAdjective(@RequestBody Adjective adjective) {
        return adjectiveRepository.save(adjective);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Adjective> updateAdjective(@PathVariable Integer id, @RequestBody Adjective adjective) {
        if (!adjectiveRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adjective.setId(id);
        return ResponseEntity.ok(adjectiveRepository.save(adjective));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdjective(@PathVariable Integer id) {
        if (!adjectiveRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adjectiveRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public long getAdjectiveCount() {
        return adjectiveRepository.count();
    }
}

