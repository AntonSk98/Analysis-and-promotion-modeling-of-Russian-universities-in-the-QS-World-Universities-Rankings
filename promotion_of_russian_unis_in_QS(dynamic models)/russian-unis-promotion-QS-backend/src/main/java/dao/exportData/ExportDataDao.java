package dao.exportData;

import entities.UniversityModelingPromotion;

import java.util.List;

public interface ExportDataDao {

    List<UniversityModelingPromotion> getPromotionInfoByUniIdAndCriterionId(int universityId, int criterionId);

    int getUniversityIdByUniversityName(String universityName);

    int getCriterionIdByUniversityCriterion(String criterionName);
}
