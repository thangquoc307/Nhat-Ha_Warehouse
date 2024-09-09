package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String partNumber;
    @Column(length = 1000)
    private String description;
    private Integer count;
    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<ItemSerial> itemSerials;
    @ManyToOne
    @JoinColumn(name = "stockNodeId", referencedColumnName = "id")
    private StockNote stockNote;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, String partNumber, String description, Integer count, StockNote stockNote) {
        this.id = id;
        this.partNumber = partNumber;
        this.description = description;
        this.count = count;
        this.stockNote = stockNote;
        this.isDelete = false;
    }
}
