package org.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetImageDto {
    private Integer idGet;
    private Boolean isInbound;
    private String name;
    private LocalDate releaseDate;
    private Boolean hasImage;
    private Integer count;
}
