package org.warehouse.model.outbound;

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
@Table(name = "outbound_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutboundItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String partNumber;
    @Column(length = 1000)
    private String description;
    private Integer count;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    @OneToMany(mappedBy = "outboundItem")
    @JsonBackReference
    private List<OutboundItemSerial> outboundItemSerials;
    @ManyToOne
    @JoinColumn(name = "outboundId", referencedColumnName = "id")
    private Outbound outbound;
    @ManyToOne
    @JoinColumn(name = "manufacturerId", referencedColumnName = "id")
    private Manufacturer manufacturer;

    public OutboundItem(Integer id) {
        this.id = id;
    }

    public OutboundItem(Integer id, String partNumber, String description, Integer count, Outbound outbound) {
        this.id = id;
        this.partNumber = partNumber;
        this.description = description;
        this.count = count;
        this.outbound = outbound;
        this.isDelete = false;
    }
}