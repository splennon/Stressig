package org.overworld.stressig.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "verbs", schema = "short")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verb {
    
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "de", nullable = false, length = 100)
    private String de;
    
    @Column(name = "en", nullable = false, length = 100)
    private String en;
    
    @Column(name = "rank", nullable = false)
    private Integer rank;
    
    @Column(name = "praesens_ich", length = 100)
    private String praesensIch;
    
    @Column(name = "praesens_du", length = 100)
    private String praesensDu;
    
    @Column(name = "praesens_er_sie_es", length = 100)
    private String praesensErSieEs;
    
    @Column(name = "praeteritum_ich", length = 100)
    private String praeteritumIch;
    
    @Column(name = "partizip_ii", length = 100)
    private String partizipII;
    
    @Column(name = "konjunktiv_ii_ich", length = 100)
    private String konjunktivIIIch;
    
    @Column(name = "imperativ_singular", length = 100)
    private String imperativSingular;
    
    @Column(name = "imperativ_plural", length = 100)
    private String imperativPlural;
    
    @Column(name = "hilfsverb", length = 10)
    private String hilfsverb;
}
