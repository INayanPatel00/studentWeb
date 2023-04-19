package com.brevitaz.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SubjectDTO {
    private int id;
    private String name;

    public SubjectDTO(final String name) {
        this.name = name;
    }
}
