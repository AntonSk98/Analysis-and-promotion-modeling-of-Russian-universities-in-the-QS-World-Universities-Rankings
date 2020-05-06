package dao.universityPromotion;

import entities.CriteriaTableInQS;
import entities.RussianUniversitiesInQS;
import models.UniversityCriterionModel;

import java.time.LocalDateTime;
import java.util.List;

public interface UniversityPromotionDao {

    int getCriterionIdByCriterionName(String criterionName);
    int getUniversityIdByUniversityName(String universityName);
    UniversityCriterionModel getUniversityCriterionInfoByUniIdAndCriterionId(int uniId, int criterionId);

    List<String> getQSCriteriaList();

    void savePromotionData(LocalDateTime currentDate, double initialValue, double promotionValue, double promotionCoefficient, int criterionId, int universityId, double startDate, double targetDate, double promotionStep, boolean autoCalculatedPromotion);

    RussianUniversitiesInQS getRussianUniversityById(int universityId);

    CriteriaTableInQS getCriterionById(int criterionId);

    double getAutocalculatedPromotionCoefficient(int universityId, int criterionId, double startDate, double targetDate, double promotionStep);

    void deletePromotionDataByIds(int universityId, int criterionId);

    int getNumberOfUniversityPromotionLaunches(int universityId, int criterionId);
}
