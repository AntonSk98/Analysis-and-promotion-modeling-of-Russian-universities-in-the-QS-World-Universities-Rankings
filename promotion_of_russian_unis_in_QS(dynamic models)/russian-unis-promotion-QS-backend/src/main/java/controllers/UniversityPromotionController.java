package controllers;


import models.UniversityPromotionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.universityPromotion.UniversityPromotionService;

import java.util.List;

@CrossOrigin("*")
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
            @RequestParam(name = "promotion_step") double promotionStep,
            @RequestParam(name = "promotion_coefficient", required = false) Double promotionCoefficient
    ) {
        if (!promotionCriterion.equals("Overall Score"))
            return universityPromotionService.conductUniversityPromotionByCriterion(universityName, promotionCriterion, startDate, targetDate, promotionStep, promotionCoefficient);
        else
            return universityPromotionService.conductOverallPromotion(universityName, startDate, targetDate, promotionStep, promotionCoefficient);
    }

    @GetMapping(value = "/api/get/promotion_coefficient", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getPromotionCoefficient(
            @RequestParam(name = "university_name") String universityName,
            @RequestParam(name = "promotion_criterion") String promotionCriterion,
            @RequestParam(name = "start_date") double startDate,
            @RequestParam(name = "target_date") double targetDate,
            @RequestParam(name = "promotion_step") double promotionStep
    ) {
        return universityPromotionService.getAutoCalculatedPromotionCoefficient(universityName, promotionCriterion, startDate, targetDate, promotionStep);
    }

    @GetMapping(value = "/api/get/university_promotion_launches", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getUniversityPromotionLanches(
            @RequestParam(name = "university_name") String universityName,
            @RequestParam(name = "promotion_criterion") String promotionCriterion
    ) {
        return universityPromotionService.getUniversityPromotionLaunces(universityName, promotionCriterion);
    }

    @DeleteMapping(value = "/api/delete/promotion_data")
    public ResponseEntity<HttpStatus> deletePromotionData(
            @RequestParam(name = "university_name") String universityName,
            @RequestParam(name = "promotion_criterion") String promotionCriterion
    ) {
        universityPromotionService.deletePromotionDataByUniIAndCriterion(universityName, promotionCriterion);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
