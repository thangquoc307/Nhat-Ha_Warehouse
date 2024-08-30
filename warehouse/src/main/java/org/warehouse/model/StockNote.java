package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stock_notes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockNote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime editTime;
    private LocalDate releaseDate;
    private String so;
    private String partner;
    @Column(length = 1000)
    private String note;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "saleId", referencedColumnName = "id")
    private Saler saler;
    @ManyToOne
    @JoinColumn(name = "warehouseId", referencedColumnName = "id")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "stockNote")
    @JsonBackReference
    private List<Item> items;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;
}
