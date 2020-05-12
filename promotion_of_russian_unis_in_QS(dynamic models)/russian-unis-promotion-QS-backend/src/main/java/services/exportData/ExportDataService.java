package services.exportData;


import entities.UniversityModelingPromotion;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface ExportDataService {

    void exportPromotionDataIntoExcelByUniIdAndCriterionId(String universityName, String critetionName);

    void exportPromotionDataIntoHtmlByUniIdAndCriterionId(String universiyName, String critetionName);

    void exportPromotionDataIntoExcelFile(String universityName, String criterionName, List<UniversityModelingPromotion> universityPromotionData) throws IOException;

    void exportPromotionDataIntoHtmlFile(String universityName, String criterionName, List<UniversityModelingPromotion> universityPromotionData) throws IOException;
}
