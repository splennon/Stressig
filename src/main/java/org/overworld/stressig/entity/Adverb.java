package org.overworld.stressig.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adverbs", schema = "short")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adverb {
    
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "de", nullable = false, length = 100)
    private String de;
    
    @Column(name = "variations", length = 100)
    private String variations;
    
    @Column(name = "en", nullable = false, length = 100)
    private String en;
    
    @Column(name = "rank", nullable = false)
    private Integer rank;
}
