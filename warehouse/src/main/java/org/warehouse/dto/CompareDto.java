package org.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompareDto {
    private String partNumber;
    private String description;
    private Integer stock = 0;
    private Integer inboundCount = 0;
    private Integer outboundCount = 0;
    private String manufacturer;
    List<GetImageDto> inboundList = new ArrayList<>();
    List<GetImageDto> outboundList = new ArrayList<>();

    public CompareDto(String partNumber, String description, Integer inboundCount, String manufacturer) {
        this.partNumber = partNumber;
        this.description = description;
        this.inboundCount = inboundCount;
        this.manufacturer = manufacturer;
    }
    public CompareDto(Integer outboundCount, String partNumber, String description, String manufacturer) {
        this.partNumber = partNumber;
        this.description = description;
        this.outboundCount = outboundCount;
        this.manufacturer = manufacturer;
    }

    public CompareDto(String partNumber) {
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompareDto that = (CompareDto) o;
        return Objects.equals(
                partNumber.toLowerCase(),
                that.partNumber.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }
}
