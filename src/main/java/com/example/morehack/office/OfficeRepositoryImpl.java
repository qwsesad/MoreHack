package com.example.morehack.office;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.sql.Time;
import java.util.List;



public class OfficeRepositoryImpl implements OfficeRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Office> findByAll(Boolean rko, Boolean officeType, Boolean hasRamp, String day, Time time, List<SalePointFormat> salePointFormats, List<Function> functions) {

        String sql = "SELECT o FROM Office o WHERE 1 = 1";

        if (rko != null) {
            sql += " AND o.rko = :rko";
        }

        if (officeType != null) {
            sql += " AND o.officeType = :officeType";
        }

        if (hasRamp != null) {
            sql += " AND o.hasRamp = :hasRamp";
        }

        if (day != null && time != null)
        {
            sql += " AND o.timeStart[:day] <= :time AND o.timeClose[:day] >= :time";
        }

        if (salePointFormats != null)
        {
            sql += " AND o.salePointFormat IN :salePointFormats";
        }

        if (functions != null)
        {
            sql += " AND :functions MEMBER OF o.functions";
        }

        TypedQuery<Office> query = entityManager.createQuery(sql, Office.class);

        if (rko != null) {
            query.setParameter("rko", rko);
        }

        if (officeType != null) {
            query.setParameter("officeType", officeType);
        }

        if (hasRamp != null) {
            query.setParameter("hasRamp", hasRamp);
        }

        if (day != null && time != null)
        {
            query.setParameter("day", day);
            query.setParameter("time", time);
        }

        if (salePointFormats != null)
        {
            query.setParameter("salePointFormats", salePointFormats);
        }

        if (functions != null)
        {
            query.setParameter("functions", functions);
        }

        List<Office> result = query.getResultList();

        return result;
    }
}
