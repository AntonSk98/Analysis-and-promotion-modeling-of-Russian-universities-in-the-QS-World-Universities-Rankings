package services.exportData;

import dao.exportData.ExportDataDao;
import entities.UniversityModelingPromotion;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
public class ExportDataServiceImpl implements ExportDataService {

    @Autowired
    private ExportDataDao exportDataDao;

    @Override
    @Transactional
    public void exportPromotionDataIntoExcelByUniIdAndCriterionId(int universityId, int criterionId, String path) {
        List<UniversityModelingPromotion> universityPromotionData= exportDataDao.getPromotionInfoByUniIdAndCriterionId(universityId, criterionId);
        String universityName = exportDataDao.getUniversityNameByUniversityId(universityId);
        String criterionName = exportDataDao.getUniversityCriterionByCriterionId(criterionId);
        try {
            exportPromotionData(universityName, criterionName, path, universityPromotionData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportPromotionData(String universityName, String criterionName, String path, List<UniversityModelingPromotion> universityPromotionData) throws IOException {
        int rowNumber = 0;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(universityName + "promotion data");
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForHeader(workbook);

        row = worksheet.createRow(rowNumber);
        worksheet.addMergedRegion(new CellRangeAddress(rowNumber,rowNumber+1,0,7));
        cell = row.createCell(0, CellType.STRING);
        cell.setCellStyle(style);
        CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
        CellUtil.setVerticalAlignment(cell, VerticalAlignment.CENTER);
        cell.setCellValue(universityName + " promotion data by " + criterionName);

        rowNumber+=2;
        row = worksheet.createRow(rowNumber);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Promotion date");
        cell.setCellStyle(style);
        worksheet.setColumnWidth(0, 6500);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Promotion coefficient");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(1);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Start date");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(2);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Start value");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(3);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Target date");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(4);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Promotion value");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(5);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Promotion step");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(6);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Coefficient is auto-calculated");
        cell.setCellStyle(style);
        worksheet.autoSizeColumn(7);

        for (UniversityModelingPromotion universityPromotionRow: universityPromotionData) {
            rowNumber ++;
            row = worksheet.createRow(rowNumber);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(universityPromotionRow.getCalculationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

            cell = row.createCell(1, CellType.STRING);
            double promotionCoefficient = universityPromotionRow.getPromotionCoefficient();
            if (promotionCoefficient == -1)
                cell.setCellValue("N/A");
            else
                cell.setCellValue(promotionCoefficient);

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(universityPromotionRow.getStartDate());

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(universityPromotionRow.getStartValue());

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(universityPromotionRow.getTargetDate());

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(universityPromotionRow.getPromotionValue());

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue(universityPromotionRow.getPromotionStep());

            cell = row.createCell(7, CellType.BOOLEAN);
            cell.setCellValue(universityPromotionRow.isAutoCalculatedPromotion());
        }

        File file = new File(path+"\\employee.xls");
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
        workbook.close();
    }

    private HSSFCellStyle createStyleForHeader(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
