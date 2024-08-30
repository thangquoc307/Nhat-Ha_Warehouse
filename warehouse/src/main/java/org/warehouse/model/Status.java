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
@Table(name = "status")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    0 - available
//    1 - hold
//    2 - done
//    3 - delete
    @OneToMany(mappedBy = "status")
    @JsonBackReference
    private List<ItemSerial> itemSerials;
}
