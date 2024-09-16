package org.warehouse.model.inbound;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.warehouse.model.Warehouse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "inbounds")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inbound implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String inboundCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String locationFrom;
    @Column(length = 1000)
    private String note;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "warehouseId", referencedColumnName = "id")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "inbound")
    @JsonBackReference
    private List<InboundItem> inboundItems;

    public Inbound(String inboundCode, LocalDate releaseDate, String locationFrom, String note, Warehouse warehouse) {
        this.inboundCode = inboundCode;
        this.releaseDate = releaseDate;
        this.locationFrom = locationFrom;
        this.note = note;
        this.warehouse = warehouse;
        this.isDelete = false;
    }
}
