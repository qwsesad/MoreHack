package com.example.morehack.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;


public interface OfficeRepository extends JpaRepository<Office, Integer>{
    List<Office> findByRko(Boolean rko);
    List<Office> findByOfficeType(String officeType);
    List<Office> findByHasRamp(Boolean hasRamp);

    @Query(value = "SELECT * FROM Office o WHERE o.timeStart[:day] <= :time and o.timeClose[:day] >= :time", nativeQuery = true)
    List<Office> findOfficeByDayAndTimeNamedParamsNative(@Param("day") Integer day, @Param("time") Time time);

    @Query(value = "SELECT * FROM Office o WHERE o.timeStart[:day] <= :timeIndividual and o.timeClose[:day] >= :timeIndividual", nativeQuery = true)
    List<Office> findOfficeByDayAndTimeIndividualNamedParamsNative(@Param("day") Integer day, @Param("timeIndividual") Time timeIndividual);

    List<Office> findByLatitudeBetweenAndLongitudeBetween(Double latitudeLeft, Double latitudeRight, Double longitudeDown, Double longitudeUp);

    List<Office> findByFunctionsIn(List<Function> functions);

    List<Office> findBySalePointFormatIn(List<SalePointFormat> salePointFormats);
}
