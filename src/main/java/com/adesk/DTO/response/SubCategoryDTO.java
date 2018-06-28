package com.adesk.DTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubCategoryDTO {
    private int id;
    private String subCategoryName;

    public SubCategoryDTO(int id, String subCategoryName) {
        this.id = id;
        this.subCategoryName = subCategoryName;
    }
}
