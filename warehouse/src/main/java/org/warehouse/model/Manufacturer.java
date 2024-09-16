package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.warehouse.model.inbound.Inbound;
import org.warehouse.model.inbound.InboundItem;
import org.warehouse.model.outbound.Outbound;
import org.warehouse.model.outbound.OutboundItem;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manufacturers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;
    @OneToMany(mappedBy = "manufacturer")
    @JsonBackReference
    private List<OutboundItem> outboundItems;
    @OneToMany(mappedBy = "manufacturer")
    @JsonBackReference
    private List<InboundItem> inboundItems;

    public Manufacturer(Integer id, String name, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
    }

    public Manufacturer(String name) {
        this.name = name;
        this.isDelete = false;
    }
}
