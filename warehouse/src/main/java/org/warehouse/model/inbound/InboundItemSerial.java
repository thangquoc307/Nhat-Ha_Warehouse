package org.warehouse.model.inbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "inbound_items_serials")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InboundItemSerial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serial;

    @ManyToOne
    @JoinColumn(name = "inboundItemId", referencedColumnName = "id")
    private InboundItem inboundItem;

    public InboundItemSerial(String serial, InboundItem inboundItem) {
        this.serial = serial;
        this.inboundItem = inboundItem;
    }
}
