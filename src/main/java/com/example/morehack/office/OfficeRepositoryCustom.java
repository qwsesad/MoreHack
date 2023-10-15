package com.example.morehack.office;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface OfficeRepositoryCustom {

    List<Office> findByAll(Boolean rko, Boolean officeType, Boolean hasRamp, String day, Time time, List<SalePointFormat> salePointFormats, List<Function> functions);

}
