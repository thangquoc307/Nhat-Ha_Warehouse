package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.warehouse.model.inbound.Inbound;
import org.warehouse.model.outbound.Outbound;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    @OneToMany(mappedBy = "warehouse")
    @JsonBackReference
    private List<Outbound> outbounds;
    @OneToMany(mappedBy = "warehouse")
    @JsonBackReference
    private List<Inbound> inbounds;

    public Warehouse(String name) {
        this.name = name;
        this.isDelete = false;
    }

    public Warehouse(Integer id, String name, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
    }

    public Warehouse(Integer id) {
        this.id = id;
    }
}
