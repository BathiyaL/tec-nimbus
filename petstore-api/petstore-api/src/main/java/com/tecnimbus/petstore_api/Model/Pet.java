package com.tecnimbus.petstore_api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    //PhotoUrlArray photoUrls;
    //private Category category;
//    @OneToMany(mappedBy = "id")
//    private Tag[] tags;
}

