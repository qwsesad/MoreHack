package com.example.morehack.office;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Boolean rko;

    @Column(nullable = false)
    private String officeType;

    @Column(nullable = false)
    private Boolean hasRamp;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @ElementCollection
    @CollectionTable(name="TimeStart", joinColumns = @JoinColumn(name = "office_id"))
    @Column(name = "timeStart")
    private List<Time> timeStart = new ArrayList<Time>();

    @ElementCollection
    @CollectionTable(name="TimeClose", joinColumns = @JoinColumn(name = "office_id"))
    @Column(name = "timeClose")
    private List<Time> timeClose = new ArrayList<Time>();

    @ElementCollection
    @CollectionTable(name="TimeStartIndividual", joinColumns = @JoinColumn(name = "office_id"))
    @Column(name = "timeStartIndividual")
    private List<Time> timeStartIndividual = new ArrayList<Time>();

    @ElementCollection
    @CollectionTable(name="TimeCloseIndividual", joinColumns = @JoinColumn(name = "office_id"))
    @Column(name = "timeCloseIndividual")
    private List<Time> timeCloseIndividual = new ArrayList<Time>();

    @ManyToMany
    @JoinTable(
            name = "office_function",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "function_id"))
    List<Function> functions;

    @ManyToOne
    @JoinColumn(name = "salepointformat_id")
    private SalePointFormat salePointFormat;
}
