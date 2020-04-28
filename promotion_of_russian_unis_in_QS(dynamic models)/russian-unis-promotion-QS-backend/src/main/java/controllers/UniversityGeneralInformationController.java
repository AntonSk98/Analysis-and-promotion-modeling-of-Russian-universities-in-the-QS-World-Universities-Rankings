package controllers;

import models.UniversityClassificationModel;
import models.UniversityCriteriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.universityGeneralInformation.UniversityGeneralInformationService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class UniversityGeneralInformationController {

    @Autowired
    private UniversityGeneralInformationService universityGeneralInformationService;

    @GetMapping(value = "/get/university_classification/{university_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityClassificationModel> getUniversityClassificationById(@PathVariable int university_id) {
        return universityGeneralInformationService.getUniversityClassificationById(university_id);
    }

    @GetMapping(value = "/get/university_criteria/{university_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityCriteriaModel> getUniversityCriteriaById(@PathVariable int university_id) {
        return universityGeneralInformationService.getUniversityCriteriaById(university_id);
    }
}
