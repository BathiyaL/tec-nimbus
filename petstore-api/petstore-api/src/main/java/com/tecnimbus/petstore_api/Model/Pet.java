package com.tecnimbus.petstore_api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private Long id;
    private String name;
    private String status;
    private String[] photoUrls;
    private Category category;
    private Tag[] tags;
}
