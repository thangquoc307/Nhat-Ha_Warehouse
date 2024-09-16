package org.warehouse.model.inbound;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.warehouse.model.Manufacturer;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "inbound_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InboundItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String partNumber;
    @Column(length = 1000)
    private String description;
    private Integer count;

    @OneToMany(mappedBy = "inboundItem", orphanRemoval = true)
    @JsonBackReference
    private List<InboundItemSerial> inboundItemSerials;
    @ManyToOne
    @JoinColumn(name = "inboundId", referencedColumnName = "id")
    private Inbound inbound;
    @ManyToOne
    @JoinColumn(name = "manufacturerId", referencedColumnName = "id")
    private Manufacturer manufacturer;

    public InboundItem(Integer id) {
        this.id = id;
    }
}
