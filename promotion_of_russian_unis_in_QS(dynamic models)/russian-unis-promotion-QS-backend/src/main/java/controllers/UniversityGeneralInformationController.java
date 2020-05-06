package controllers;

import models.UniversityClassificationModel;
import models.UniversityCriterionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.universityGeneralInformation.UniversityGeneralInformationService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class UniversityGeneralInformationController {

    @Autowired
    private UniversityGeneralInformationService universityGeneralInformationService;

    @GetMapping(value = "/get/university_classification", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityClassificationModel> getUniversityClassificationById(
            @RequestParam(name = "university_id") int university_id
    ) {
        return universityGeneralInformationService.getUniversityClassificationById(university_id);
    }

    @GetMapping(value = "/get/university_criteria", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityCriterionModel> getUniversityCriteriaById(
            @RequestParam(name = "university_id") int university_id
    ) {
        return universityGeneralInformationService.getUniversityCriteriaById(university_id);
    }
}
