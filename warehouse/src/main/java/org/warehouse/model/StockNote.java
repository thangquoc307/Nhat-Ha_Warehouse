package org.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public StockNote(Integer id, LocalDate releaseDate, String so, String partner, String note, Saler saler, Warehouse warehouse) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.so = so;
        this.partner = partner;
        this.note = note;
        this.saler = saler;
        this.warehouse = warehouse;
        this.isDelete = false;
    }

    public StockNote(Integer id) {
        this.id = id;
    }
}
