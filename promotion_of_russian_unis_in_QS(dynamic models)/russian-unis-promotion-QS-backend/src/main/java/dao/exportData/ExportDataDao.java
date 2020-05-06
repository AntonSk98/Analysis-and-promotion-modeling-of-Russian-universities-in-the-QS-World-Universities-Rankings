package dao.exportData;

import entities.UniversityModelingPromotion;

import java.util.List;

public interface ExportDataDao {

    List<UniversityModelingPromotion> getPromotionInfoByUniIdAndCriterionId(int universityId, int criterionId);

    String getUniversityNameByUniversityId(int universityId);

    String getUniversityCriterionByCriterionId(int criterionId);
}
