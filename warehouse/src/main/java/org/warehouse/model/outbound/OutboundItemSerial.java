package org.warehouse.model.outbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name = "outbound_items_serials")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutboundItemSerial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serial;
    @ManyToOne
    @JoinColumn(name = "outboundItemId", referencedColumnName = "id")
    private OutboundItem outboundItem;

    public OutboundItemSerial(String serial, OutboundItem outboundItem) {
        this.serial = serial;
        this.outboundItem = outboundItem;
    }
}
