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

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import static j2html.TagCreator.*;

@Service
public class ExportDataServiceImpl implements ExportDataService {

    @Autowired
    private ExportDataDao exportDataDao;

    @Override
    @Transactional
    public void exportPromotionDataIntoExcelByUniIdAndCriterionId(String universityName, String criterionName) {
        int universityId = exportDataDao.getUniversityIdByUniversityName(universityName);
        int criterionId = exportDataDao.getCriterionIdByUniversityCriterion(criterionName);
        List<UniversityModelingPromotion> universityPromotionData= exportDataDao.getPromotionInfoByUniIdAndCriterionId(universityId, criterionId);
        try {
            exportPromotionDataIntoExcelFile(universityName, criterionName, universityPromotionData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void exportPromotionDataIntoHtmlByUniIdAndCriterionId(String universityName, String criterionName) {
        int universityId = exportDataDao.getUniversityIdByUniversityName(universityName);
        int criterionId = exportDataDao.getCriterionIdByUniversityCriterion(criterionName);
        List<UniversityModelingPromotion> universityPromotionData= exportDataDao.getPromotionInfoByUniIdAndCriterionId(universityId, criterionId);
        try {
            exportPromotionDataIntoHtmlFile(universityName, criterionName, universityPromotionData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportPromotionDataIntoExcelFile(String universityName, String criterionName, List<UniversityModelingPromotion> universityPromotionData) throws IOException {
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
        String path = System.getProperty("user.home") + "\\Downloads";
        System.out.println(path);
        File file = new File(path+"\\"+universityName+"_promotion_by"+criterionName+".xls");
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
        workbook.close();
    }

    @Override
    public void exportPromotionDataIntoHtmlFile(String universityName, String criterionName, List<UniversityModelingPromotion> universityPromotionData) throws IOException {
        String htmlDoc = html(
                style(".qs-color {color: #F9B21E;}"),
                style(".text-center {text-align: center;}"),
                style("table {border-collapse: collapse; display: flex; justify-content: center; min-height: calc(85vh - 2rem);}"),
                style("th, td {border:1px solid black; padding: 0.4rem;}"),
                style(".header {background-color: #F9B21E;}"),
                style(".footer_note {display: flex; justify-content:flex-end; margin-top: 1rem; font-weight: bold; height: 2rem;"),
                head(
                        title("University promotion information")
                ),
                body(
                        h1(attrs(".qs-color.text-center"), universityName + " promotion by "+criterionName),
                        table(
                                tr(attrs(".header"),
                                        th("Promotion date"),
                                        th("Promotion coefficient"),
                                        th("Start date"),
                                        th("Start value"),
                                        th("Target date"),
                                        th("Promotion value"),
                                        th("Promotion step"),
                                        th("Coefficient is auto-calculated")
                                ),
                                each(universityPromotionData, rowUniversityData -> tr(
                                        td(String.valueOf(rowUniversityData.getCalculationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)))),
                                        td(String.valueOf(rowUniversityData.getPromotionCoefficient())),
                                        td(String.valueOf(rowUniversityData.getStartDate())),
                                        td(String.valueOf(rowUniversityData.getStartValue())),
                                        td(String.valueOf(rowUniversityData.getTargetDate())),
                                        td(String.valueOf(rowUniversityData.getPromotionValue())),
                                        td(String.valueOf(rowUniversityData.getPromotionStep())),
                                        td(String.valueOf(rowUniversityData.isAutoCalculatedPromotion()).toUpperCase())
                                ))
                        )
                ),
                footer(attrs(".footer_note.qs-color"),
                        div("This webpage was generated automatically!")
                )
        ).render();
        String path = System.getProperty("user.home") + "\\Downloads";
        System.out.println(path);
        File newHtmlFile = new File(path+"\\"+universityName+"_promotion_by"+criterionName+".html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(newHtmlFile));
        writer.write(htmlDoc);
        writer.close();
    }

    private HSSFCellStyle createStyleForHeader(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}
