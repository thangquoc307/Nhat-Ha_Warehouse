package org.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name = "items_serials")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSerial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serial;
    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    private Status status;
}
