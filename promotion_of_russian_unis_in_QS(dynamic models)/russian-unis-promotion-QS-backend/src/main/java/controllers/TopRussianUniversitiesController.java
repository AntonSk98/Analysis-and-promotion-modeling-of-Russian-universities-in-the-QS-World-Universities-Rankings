package controllers;

import entities.RussianUniversitiesInQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.allRussianUniversities.TopRussianUniversitiesService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TopRussianUniversitiesController {

    @Autowired
    private TopRussianUniversitiesService topRussianUniversitiesService;

    @GetMapping(value = "/get/top_russian_universities_in_QS", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RussianUniversitiesInQS> getAllRussianTopUniversities() {
        return topRussianUniversitiesService.getAllRussianTopUniversities();
    }

}
