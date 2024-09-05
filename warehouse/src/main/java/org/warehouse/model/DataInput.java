package org.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "data_input")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String partNumber;
    private Integer count;
}
