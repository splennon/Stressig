package org.overworld.stressig.controller;

import java.util.List;
import java.util.Optional;

import org.overworld.stressig.entity.Verb;
import org.overworld.stressig.repository.VerbRepository;
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
@RequestMapping("/api/verbs")
@CrossOrigin(origins = "*")
public class VerbController {
    
    @Autowired
    private VerbRepository verbRepository;
    
    @GetMapping
    public List<Verb> getAllVerbs() {
        return verbRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Verb> getVerbById(@PathVariable Integer id) {
        Optional<Verb> verb = verbRepository.findById(id);
        return verb.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search/german")
    public List<Verb> searchByGerman(@RequestParam String word) {
        return verbRepository.findByDeContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/english")
    public List<Verb> searchByEnglish(@RequestParam String word) {
        return verbRepository.findByEnContainingIgnoreCase(word);
    }
    
    @GetMapping("/search/conjugated")
    public List<Verb> searchByConjugatedForm(@RequestParam String word) {
        return verbRepository.findByAnyConjugatedForm(word);
    }
    
    @GetMapping("/auxiliary/{hilfsverb}")
    public List<Verb> getVerbsByAuxiliary(@PathVariable String hilfsverb) {
        return verbRepository.findByHilfsverb(hilfsverb);
    }
    
    @GetMapping("/present-conjugation")
    public List<Verb> getVerbsWithPresentConjugation() {
        return verbRepository.findVerbsWithPresentConjugation();
    }
    
    @GetMapping("/partizip")
    public List<Verb> getVerbsWithPartizipII() {
        return verbRepository.findVerbsWithPartizipII();
    }
    
    @GetMapping("/rank/{maxRank}")
    public List<Verb> getVerbsByRank(@PathVariable Integer maxRank) {
        return verbRepository.findByRankLessThanEqual(maxRank);
    }
    
    @GetMapping("/ordered")
    public List<Verb> getVerbsOrderedByRank() {
        return verbRepository.findByOrderByRankAsc();
    }
    
    @PostMapping
    public Verb createVerb(@RequestBody Verb verb) {
        return verbRepository.save(verb);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Verb> updateVerb(@PathVariable Integer id, @RequestBody Verb verb) {
        if (!verbRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        verb.setId(id);
        return ResponseEntity.ok(verbRepository.save(verb));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerb(@PathVariable Integer id) {
        if (!verbRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        verbRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public long getVerbCount() {
        return verbRepository.count();
    }
}

/*
API Endpoint Summary:

ADJECTIVES (/api/adjectives):
- GET / - Get all adjectives
- GET /{id} - Get adjective by ID
- GET /search/german?word={word} - Search by German word
- GET /search/english?word={word} - Search by English word
- GET /search/variation?word={word} - Search by word variations
- GET /rank/{maxRank} - Get adjectives up to specified rank
- GET /ordered - Get all adjectives ordered by rank
- POST / - Create new adjective
- PUT /{id} - Update adjective
- DELETE /{id} - Delete adjective
- GET /count - Get total count

ADVERBS (/api/adverbs):
- Same endpoints as adjectives

NOUNS (/api/nouns):
- All basic CRUD + search endpoints
- GET /search/root?root={root} - Search by root form
- GET /search/plural?word={word} - Search by plural form
- GET /article/{article} - Get nouns by article (der, die, das, etc.)
- GET /gender/masculine - Get masculine nouns
- GET /gender/feminine - Get feminine nouns
- GET /gender/neuter - Get neuter nouns

VERBS (/api/verbs):
- All basic CRUD + search endpoints
- GET /search/conjugated?word={word} - Search by any conjugated form
- GET /auxiliary/{hilfsverb} - Get verbs by auxiliary verb
- GET /present-conjugation - Get verbs with present tense conjugation
- GET /partizip - Get verbs with Partizip II form

Example Usage:
- GET /api/nouns/gender/masculine - Returns all masculine nouns
- GET /api/verbs/search/conjugated?word=bin - Finds verbs with "bin" as any conjugated form
- GET /api/adjectives/rank/100 - Returns adjectives with rank <= 100
*/