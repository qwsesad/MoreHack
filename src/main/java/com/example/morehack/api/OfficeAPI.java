package com.example.morehack.api;

import com.example.morehack.office.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(path = "/api/offices")
@CrossOrigin(origins="http://morehack:8080")
public class OfficeAPI  {

    private OfficeRepository officeRepository;
    private FunctionRepository functionRepository;
    private SalePointFormatRepository salePointFormatRepository;
    private OfficeRepositoryCustom officeRepositoryCustom;

    public OfficeAPI(OfficeRepository officeRepository, FunctionRepository functionRepository, SalePointFormatRepository salePointFormatRepository, OfficeRepositoryCustom officeRepositoryCustom)
    {
        this.officeRepository = officeRepository;
        this.functionRepository = functionRepository;
        this.salePointFormatRepository = salePointFormatRepository;
        this.officeRepositoryCustom = officeRepositoryCustom;
    }

    @GetMapping(path = "/get", produces = "application/json")
    public Iterable<Office> getSpecOffices(@RequestParam(required = false) Boolean rko,
                                           @RequestParam(required = false) Boolean officeType,
                                           @RequestParam(required = false) Boolean hasRamp,
                                           @RequestParam(required = false) Double latitudeCenter,
                                           @RequestParam(required = false) Double longitudeCenter,
                                           @RequestParam(required = false) String day,
                                           @RequestParam(required = false) Time time,
                                           @RequestParam(required = false) List<String> salePointFormat,
                                           @RequestParam(required = false) List<String> functions)
    {
        if (rko == null && officeType == null && hasRamp == null && latitudeCenter == null && longitudeCenter == null && day == null && time == null && salePointFormat == null && functions == null)
        {
            return officeRepository.findAll();
        }
        else
        {
            List<Function> func;
            List<SalePointFormat> spf;

            if (functions != null)
                func = functionRepository.findByNameIn(functions);
            else
                func = null;
            if (salePointFormat != null)
                spf = salePointFormatRepository.findByTypeIn(salePointFormat);
            else
                spf = null;

            return officeRepositoryCustom.findByAll(rko, officeType, hasRamp, day, time, spf, func);
        }
    }

    @PostMapping(path = "/set",consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin("*")
    public Office postOffice(@RequestBody Office office)
    {
        System.out.println(office);
        return officeRepository.save(office);
    }

}
