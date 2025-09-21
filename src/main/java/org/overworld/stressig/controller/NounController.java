package org.overworld.stressig.controller;

import java.util.List;
import java.util.Optional;

import org.overworld.stressig.entity.Noun;
import org.overworld.stressig.repository.NounRepository;
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
@RequestMapping("/api/nouns")
@CrossOrigin(origins = "*")
public class NounController {
    
    @Autowired
    private NounRepository nounRepository;
    
    @GetMapping
    public List<Noun> getAllNouns() {
        return nounRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Noun> getNounById(@PathVariable Integer id) {
        Optional<Noun> noun = nounRepository.findById(id);
        return noun.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search/german")
    public List<Noun> searchByGerman(@RequestParam String word) {
        return nounRepository.findByDeContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/english")
    public List<Noun> searchByEnglish(@RequestParam String word) {
        return nounRepository.findByEnContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/root")
    public List<Noun> searchByRoot(@RequestParam String root) {
        return nounRepository.findByRootContainingIgnoreCase(root);
    }
    
    @GetMapping("/search/plural")
    public List<Noun> searchByPlural(@RequestParam String word) {
        return nounRepository.findByGermanWordOrPlural(word);
    }
    
    @GetMapping("/article/{article}")
    public List<Noun> getNounsByArticle(@PathVariable String article) {
        return nounRepository.findByArticle(article);
    }
    
    @GetMapping("/gender/masculine")
    public List<Noun> getMasculineNouns() {
        return nounRepository.findMasculineNouns();
    }
    
    @GetMapping("/gender/feminine")
    public List<Noun> getFeminineNouns() {
        return nounRepository.findFeminineNouns();
    }
    
    @GetMapping("/gender/neuter")
    public List<Noun> getNeuterNouns() {
        return nounRepository.findNeuterNouns();
    }
    
    @GetMapping("/rank/{maxRank}")
    public List<Noun> getNounsByRank(@PathVariable Integer maxRank) {
        return nounRepository.findByRankLessThanEqual(maxRank);
    }
    
    @GetMapping("/ordered")
    public List<Noun> getNounsOrderedByRank() {
        return nounRepository.findByOrderByRankAsc();
    }
    
    @PostMapping
    public Noun createNoun(@RequestBody Noun noun) {
        return nounRepository.save(noun);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Noun> updateNoun(@PathVariable Integer id, @RequestBody Noun noun) {
        if (!nounRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        noun.setId(id);
        return ResponseEntity.ok(nounRepository.save(noun));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoun(@PathVariable Integer id) {
        if (!nounRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        nounRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public long getNounCount() {
        return nounRepository.count();
    }
}

