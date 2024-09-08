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
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    public ItemSerial(String serial, Item item) {
        this.serial = serial;
        this.item = item;
        this.isDelete = false;
    }

    public ItemSerial(Integer id, String serial, Boolean isDelete) {
        this.id = id;
        this.serial = serial;
        this.isDelete = isDelete;
    }
}
