package com.example.morehack.office;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.List;

@Entity
@Data
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Time awaitTime;

    @Column(nullable = false)
    private Integer peopleRightNow = 1;

    @ManyToMany(mappedBy = "functions")
    List<Office> offices;

}
