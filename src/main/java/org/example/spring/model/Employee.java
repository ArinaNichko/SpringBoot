package org.example.spring.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee {

    @NotNull
    private Integer id;
    private String name;
    private String passportNumber;
    private String education;
    private String country;
}
