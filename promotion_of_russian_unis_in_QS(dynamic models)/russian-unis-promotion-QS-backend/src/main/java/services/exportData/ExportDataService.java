package services.exportData;


public interface ExportDataService {
    void exportPromotionDataIntoExcelByUniIdAndCriterionId(int universityId, int criterionId, String path);
}
