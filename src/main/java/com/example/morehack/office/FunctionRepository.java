package com.example.morehack.office;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, Integer> {

    Function findByName(String name);

    List<Function> findByNameIn(List<String> names);

}
