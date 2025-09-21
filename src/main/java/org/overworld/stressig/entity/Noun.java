package org.overworld.stressig.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name = "nouns", schema = "short")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noun {
    
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "root", nullable = false, length = 100)
    private String root;
    
    @Column(name = "de", nullable = false, length = 100)
    private String de;
    
    @Column(name = "article", nullable = false, length = 7)
    private String article;
    
    @Column(name = "en", nullable = false, columnDefinition = "TEXT")
    private String en;
    
    @Column(name = "plural", length = 100)
    private String plural;
    
    @Column(name = "rank", nullable = false)
    private Integer rank;
}
