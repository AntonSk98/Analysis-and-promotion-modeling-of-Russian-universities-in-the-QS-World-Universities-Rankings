package services.universityPromotion;

import models.UniversityPromotionModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface UniversityPromotionService {

    List<UniversityPromotionModel> conductUniversityPromotionByCriterion(String universityName, String promotionCriterion, double startDate, double targetDate, double promotionStep, Double coefficient);

    List<UniversityPromotionModel> conductOverallPromotion(String universityName, double startDate, double targetDate, double promotionStep, Double coefficient);

    void savePromotionData(int universityId, int criterionId, double initialValue, double promotionValue, double promotionCoefficient, double startDate, double targetDate, double promotionStep, boolean autoCalculatedPromotion);

    double calculateOverallScore(
            double academicReputation, double employerReputation, double facultyStudentRatio,
            double citationsPerFaculty, double internationalFacultyRatio, double internationalStudentRatio
    );

    Map<String, List<UniversityPromotionModel>> getMapOfUniversityPromotionByAllCriteria(String universityName, double startDate, double targetDate, double promotionStep, Double coefficient);

    List<String> getCriteraList();

    List<UniversityPromotionModel> conductUniversityPromotion(double previousDate, double previousValue, double startPromotionDate, double startPromotionValue, double targetDate, double stepOfPromotion, double adjustedTime, Double coeeficient);

    double adjustPromotionCoefficient(double starPromotionDate, double startPromotionValue, double desiredValue, double targetDate, double finalPromotionValue, double stepOfPromotion, double promotionCoefficient, double adjustedTime);

    double getDesiredValueForPromotion(double startPromotionValue, double secondPromotionValue, double years2Model);

    LinkedList<UniversityPromotionModel> getPromotionValues(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double promotionCoefficient, double adjustedTime);

    double getPromotionCoefficient(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime);

    double getLeftEdge(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime);

    double getRightEdge(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime);

    boolean getIsPromotionAscendant(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime);

    boolean isAutoCalculatedPromotion(Double coefficient);

    double getAutoCalculatedPromotionCoefficient(String universityName, String promotionCriterion, double startDate, double targetDate, double promotionStep);

    void deletePromotionDataByUniIAndCriterion(String universityName, String promotionCriterion);

    int getUniversityPromotionLaunces(String universityName, String promotionCriterion);
}
