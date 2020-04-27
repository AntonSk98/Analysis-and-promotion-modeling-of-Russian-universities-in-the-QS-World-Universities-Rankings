package controllers;

import entities.RussianUniversitiesInQS;
import entities.UniversityClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.RussianUniversititesService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RussianUniversitiesController {

    @Autowired
    private RussianUniversititesService russianUniversititesService;

    @GetMapping(value = "/api/russian_universities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RussianUniversitiesInQS> getAllRussianTopUniversities() {
        return russianUniversititesService.getAllRussianTopUniversities();
    }

    @GetMapping(value = "api/university_classification/{university_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityClassification> getAllUniversityClassificationByID(@PathVariable int university_id) {
        return russianUniversititesService.getUniversityClassificationById(university_id);
    }
}
