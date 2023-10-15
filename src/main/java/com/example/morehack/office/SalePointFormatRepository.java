package com.example.morehack.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalePointFormatRepository extends JpaRepository<SalePointFormat, Integer> {

    List<SalePointFormat> findByTypeIn(List<String> types);

}
