package controllers;

import models.ExportDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.exportData.ExportDataService;

@RestController
public class ExportInformationController {

    @Autowired
    private ExportDataService dataService;

    @PostMapping(value = "/export/data/into/excel")
    public ResponseEntity exportDataIntoExcelFile(@RequestBody ExportDataModel exportDataModel) {
        dataService.exportPromotionDataIntoExcelByUniIdAndCriterionId(
                exportDataModel.getUniversityId(),
                exportDataModel.getCriterionId(),
                exportDataModel.getPath());
        return ResponseEntity.ok().build();
    }
}
