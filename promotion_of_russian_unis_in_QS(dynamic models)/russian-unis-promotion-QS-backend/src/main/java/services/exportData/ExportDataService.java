package services.exportData;


import entities.UniversityModelingPromotion;

import java.io.IOException;
import java.util.List;

public interface ExportDataService {
    void exportPromotionDataIntoExcelByUniIdAndCriterionId(int universityId, int criterionId, String path);

    void exportPromotionDataIntoHtmlByUniIdAndCriterionId(int universityId, int criterionId, String path);

    void exportPromotionDataIntoExcelFile(String universityName, String criterionName, String path, List<UniversityModelingPromotion> universityPromotionData) throws IOException;

    void exportPromotionDataIntoHtmlFile(String universityName, String criterionName, String path, List<UniversityModelingPromotion> universityPromotionData) throws IOException;
}
