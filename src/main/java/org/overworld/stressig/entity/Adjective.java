// Entity Classes

package org.overworld.stressig.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "adjectives", schema = "short")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adjective {
    
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
