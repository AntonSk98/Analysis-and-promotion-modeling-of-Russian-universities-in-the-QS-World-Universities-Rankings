package controllers;

import models.UniversityPromotionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.universityPromotion.UniversityPromotionService;

import java.util.List;


@RestController
public class UniversityPromotionController {

    @Autowired
    private UniversityPromotionService universityPromotionService;

    @GetMapping(value = "/api/promote", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UniversityPromotionModel> getUniversityPromotionByCriterion(
            @RequestParam(name = "university_name") String universityName,
            @RequestParam(name = "promotion_criterion") String promotionCriterion,
            @RequestParam(name = "start_date") double startDate,
            @RequestParam(name = "target_date") double targetDate,
            @RequestParam(name = "promotion_step") double promotionStep
    ) {
        if (!promotionCriterion.equals("Overall Score"))
            return universityPromotionService.conductUniversityPromotionByCriterion(universityName, promotionCriterion, startDate, targetDate, promotionStep);
        else
            return universityPromotionService.conductOverallPromotion(universityName, startDate, targetDate, promotionStep);
    }
}
