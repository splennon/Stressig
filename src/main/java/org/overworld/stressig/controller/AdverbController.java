package org.overworld.stressig.controller;

import java.util.List;
import java.util.Optional;

import org.overworld.stressig.entity.Adverb;
import org.overworld.stressig.repository.AdverbRepository;
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
@RequestMapping("/api/adverbs")
@CrossOrigin(origins = "*")
public class AdverbController {
    
    @Autowired
    private AdverbRepository adverbRepository;
    
    @GetMapping
    public List<Adverb> getAllAdverbs() {
        return adverbRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Adverb> getAdverbById(@PathVariable Integer id) {
        Optional<Adverb> adverb = adverbRepository.findById(id);
        return adverb.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search/german")
    public List<Adverb> searchByGerman(@RequestParam String word) {
        return adverbRepository.findByDeContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/english")
    public List<Adverb> searchByEnglish(@RequestParam String word) {
        return adverbRepository.findByEnContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/variation")
    public List<Adverb> searchByVariation(@RequestParam String word) {
        return adverbRepository.findByGermanWordOrVariation(word);
    }
    
    @GetMapping("/rank/{maxRank}")
    public List<Adverb> getAdverbsByRank(@PathVariable Integer maxRank) {
        return adverbRepository.findByRankLessThanEqual(maxRank);
    }
    
    @GetMapping("/ordered")
    public List<Adverb> getAdverbsOrderedByRank() {
        return adverbRepository.findByOrderByRankAsc();
    }
    
    @PostMapping
    public Adverb createAdverb(@RequestBody Adverb adverb) {
        return adverbRepository.save(adverb);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Adverb> updateAdverb(@PathVariable Integer id, @RequestBody Adverb adverb) {
        if (!adverbRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adverb.setId(id);
        return ResponseEntity.ok(adverbRepository.save(adverb));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdverb(@PathVariable Integer id) {
        if (!adverbRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adverbRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public long getAdverbCount() {
        return adverbRepository.count();
    }
}

