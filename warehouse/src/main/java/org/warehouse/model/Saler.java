package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.warehouse.model.outbound.Outbound;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "salers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Saler implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "saler")
    @JsonBackReference
    private List<Outbound> outbounds;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    public Saler(String name) {
        this.name = name;
        this.isDelete = false;
    }

    public Saler(Integer id, String name, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
    }

    public Saler(Integer id) {
        this.id = id;
    }
}

