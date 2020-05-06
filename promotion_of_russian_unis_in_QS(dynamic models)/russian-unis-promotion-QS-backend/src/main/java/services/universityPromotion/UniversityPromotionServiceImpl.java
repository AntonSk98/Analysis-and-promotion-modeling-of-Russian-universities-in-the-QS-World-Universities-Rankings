package services.universityPromotion;

import dao.universityPromotion.UniversityPromotionDao;
import models.UniversityCriterionModel;
import models.UniversityPromotionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UniversityPromotionServiceImpl implements UniversityPromotionService {

    @Autowired
    private UniversityPromotionDao universityPromotionDao;

    private double finalPromotionCoefficient;

    @Override
    @Transactional
    public List<UniversityPromotionModel> conductUniversityPromotionByCriterion(String universityName, String promotionCriterion, double startDate, double targetDate, double promotionStep, Double coefficient) {
        final int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
        final int criterionId = universityPromotionDao.getCriterionIdByCriterionName(promotionCriterion);
        UniversityCriterionModel universityCriterionInfo = universityPromotionDao
                .getUniversityCriterionInfoByUniIdAndCriterionId(universityId, criterionId);
        LinkedList<UniversityPromotionModel> universityPromotiondata = new LinkedList<>();
        universityPromotiondata.add(new UniversityPromotionModel(startDate, universityCriterionInfo.getRank2020()));
        universityPromotiondata.addAll(conductUniversityPromotion(
                startDate - 1,
                universityCriterionInfo.getRank2019(),
                startDate,
                universityCriterionInfo.getRank2020(),
                targetDate,
                promotionStep,
                1,
                coefficient
        ));
        savePromotionData(universityId, criterionId, universityPromotiondata.getFirst().getPromotionValue(), universityPromotiondata.getLast().getPromotionValue(), this.finalPromotionCoefficient, startDate, targetDate, promotionStep, isAutoCalculatedPromotion(coefficient));
        return universityPromotiondata;
    }

    @Override
    @Transactional
    public List<UniversityPromotionModel> conductOverallPromotion(String universityName, double startDate, double targetDate, double promotionStep, Double coefficient) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        Map<String, List<UniversityPromotionModel>> universityPromotionByAllCritera = getMapOfUniversityPromotionByAllCriteria(universityName, startDate, targetDate, promotionStep, coefficient);
        LinkedList<UniversityPromotionModel> overallPromotion = new LinkedList<>();
        double academicReputation;
        double employerReputation;
        double facultyStudentRatio;
        double citationsPerFaculty;
        double internationalFacultyRatio;
        double internationalStudentRatio;
        double currentDate = startDate;
        for (int i = 0; i < ((targetDate-startDate)/promotionStep) + 1; i++) {
            academicReputation = universityPromotionByAllCritera.get("Academic Reputation").get(i).getPromotionValue();
            employerReputation = universityPromotionByAllCritera.get("Employer Reputation").get(i).getPromotionValue();
            facultyStudentRatio = universityPromotionByAllCritera.get("Faculty&Student Ratio").get(i).getPromotionValue();
            citationsPerFaculty = universityPromotionByAllCritera.get("Citations Per Faculty").get(i).getPromotionValue();
            internationalFacultyRatio = universityPromotionByAllCritera.get("International Faculty Ratio").get(i).getPromotionValue();
            internationalStudentRatio = universityPromotionByAllCritera.get("International Student Ratio").get(i).getPromotionValue();
            overallPromotion.add(new UniversityPromotionModel(
                    Double.parseDouble(decimalFormat.format(currentDate)),
                    Double.parseDouble(decimalFormat.format(calculateOverallScore(
                            academicReputation, employerReputation, facultyStudentRatio,
                            citationsPerFaculty, internationalFacultyRatio, internationalStudentRatio)
                    ))));
            currentDate += promotionStep;
        }
        int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
        int criterionId = universityPromotionDao.getCriterionIdByCriterionName("Overall Score");
        savePromotionData(universityId, criterionId, overallPromotion.getFirst().getPromotionValue(), overallPromotion.getLast().getPromotionValue(), -1, startDate, targetDate, promotionStep, isAutoCalculatedPromotion(coefficient));
        return overallPromotion;
    }

    @Override
    public void savePromotionData(int universityId, int criterionId, double initialValue, double promotionValue, double promotionCoefficient, double startDate, double targetDate, double promotionStep, boolean autoCalculatedPromotion) {
        LocalDateTime currentDate = LocalDateTime.now();
        universityPromotionDao.savePromotionData(currentDate, initialValue, promotionValue, promotionCoefficient, criterionId, universityId, startDate, targetDate, promotionStep, autoCalculatedPromotion);
    }

    @Override
    public double calculateOverallScore(
            double academicReputation, double employerReputation, double facultyStudentRatio,
            double citationsPerFaculty, double internationalFacultyRatio, double internationalStudentRatio
    ) {
        return 0.4 * academicReputation + 0.1 * employerReputation + 0.2 * facultyStudentRatio + 0.2 * citationsPerFaculty + 0.05 * internationalFacultyRatio + 0.05 * internationalStudentRatio;
    }

    @Override
    @Transactional
    public Map<String, List<UniversityPromotionModel>> getMapOfUniversityPromotionByAllCriteria(String universityName, double startDate, double targetDate, double promotionStep, Double coefficient) {
        List<String> criteriaList = getCriteraList();
        Map<String, List<UniversityPromotionModel>> universityPromotionByAllCritera = new LinkedHashMap<>();
        for (String criterion: criteriaList) {
            int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
            int criterionId = universityPromotionDao.getCriterionIdByCriterionName(criterion);
            List<UniversityPromotionModel> universityPromotionData = new LinkedList<>();
            UniversityCriterionModel universityCriterionInfo = universityPromotionDao
                    .getUniversityCriterionInfoByUniIdAndCriterionId(
                            universityId,
                            criterionId
                    );
            universityPromotionData.add(new UniversityPromotionModel(startDate, universityCriterionInfo.getRank2020()));
            universityPromotionData.addAll(conductUniversityPromotion(
                    startDate - 1,
                    universityCriterionInfo.getRank2019(),
                    startDate,
                    universityCriterionInfo.getRank2020(),
                    targetDate,
                    promotionStep,
                    1,
                    coefficient
            ));
            universityPromotionByAllCritera.put(criterion, universityPromotionData);
        }
        return universityPromotionByAllCritera;
    }

    @Override
    @Transactional
    public List<String> getCriteraList() {
        return universityPromotionDao.getQSCriteriaList();
    }

    @Override
    public List<UniversityPromotionModel> conductUniversityPromotion(double previousDate, double previousValue, double startPromotionDate, double startPromotionValue, double targetDate, double stepOfPromotion, double adjustedTime, Double coefficient) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        final double desiredValue = getDesiredValueForPromotion(previousValue, startPromotionValue, targetDate - startPromotionDate);
        if (coefficient == null) {
            coefficient = getPromotionCoefficient(previousDate, previousValue, startPromotionValue, startPromotionDate, stepOfPromotion, adjustedTime);
            double finalPromotionValue = getPromotionValues(startPromotionDate, startPromotionValue, desiredValue, targetDate, stepOfPromotion, coefficient, adjustedTime).getLast().getPromotionValue();
            if (finalPromotionValue > desiredValue)
                coefficient = adjustPromotionCoefficient(startPromotionDate, startPromotionValue, desiredValue, targetDate, finalPromotionValue, stepOfPromotion, coefficient, adjustedTime);
        }
        LinkedList<UniversityPromotionModel> universityPromotionData = getPromotionValues(startPromotionDate, startPromotionValue, desiredValue, targetDate, stepOfPromotion, coefficient, adjustedTime);
        this.finalPromotionCoefficient = Double.parseDouble(decimalFormat.format(coefficient));
        return universityPromotionData;
    }

    @Override
    public double adjustPromotionCoefficient(double starPromotionDate, double startPromotionValue, double desiredValue, double targetDate, double finalPromotionValue, double stepOfPromotion, double promotionCoefficient, double adjustedTime) {
        while (finalPromotionValue > desiredValue) {
            if (promotionCoefficient < 0.5)
                finalPromotionValue = getPromotionValues(starPromotionDate, startPromotionValue, desiredValue, targetDate, stepOfPromotion, promotionCoefficient +=0.05, adjustedTime).getLast().getPromotionValue();
            else
                finalPromotionValue = getPromotionValues(starPromotionDate, startPromotionValue, desiredValue, targetDate, stepOfPromotion, promotionCoefficient -=0.05, adjustedTime).getLast().getPromotionValue();
        }
        return promotionCoefficient;
    }

    @Override
    public double getDesiredValueForPromotion(double startPromotionValue, double secondPromotionValue, double years2Model) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double desiredValue = startPromotionValue >= secondPromotionValue ? secondPromotionValue + years2Model : secondPromotionValue + ((secondPromotionValue - startPromotionValue) * years2Model);
        return desiredValue < 100 ? Double.parseDouble(decimalFormat.format(desiredValue)) : 100;
    }

    @Override
    public LinkedList<UniversityPromotionModel> getPromotionValues(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double promotionCoefficient, double adjustedTime) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        double currentValue = startValue;
        double currentDate = startDate;
        double shortfallValue;
        double modelingYears = finishDate - startDate;
        LinkedList<UniversityPromotionModel> universityPromotionInfo = new LinkedList<UniversityPromotionModel>();
        for (int i = 0; i < modelingYears / stepOfPromotion; i++) {
            currentDate += stepOfPromotion;
            shortfallValue = desiredValue - currentValue;
            if (currentValue > 1000)
                continue;
            else
                currentValue += (Math.pow( Math.pow(shortfallValue, 2) * currentValue / desiredValue, promotionCoefficient/adjustedTime)) * stepOfPromotion;
            universityPromotionInfo.add(new UniversityPromotionModel(Double.parseDouble(decimalFormat.format(currentDate)), Double.parseDouble(decimalFormat.format(currentValue))));
        }
        return universityPromotionInfo;
    }

    @Override
    public double getPromotionCoefficient(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime) {
        if (startValue == desiredValue) {
            return 0.5;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double leftEdge =  getLeftEdge(startDate, startValue, desiredValue, finishDate, stepOfPromotion, adjustedTime);
        double rightEdge =  getRightEdge(startDate, startValue, desiredValue, finishDate, stepOfPromotion, adjustedTime);
        if (rightEdge > 100) {
            double coefficient = 1.0;
            while (rightEdge > 100) {
                rightEdge =  getPromotionValues(startDate, startValue, desiredValue, finishDate, stepOfPromotion, coefficient -= 0.05, adjustedTime).getLast().getPromotionValue();
            }
        }
        double averagePromotionValue = (leftEdge + rightEdge) / 2;
        double currentPromotionValue = 0;
        double promotionCoefficient;
        boolean isPromotionAscendant = getIsPromotionAscendant(startDate, startValue, desiredValue, finishDate, stepOfPromotion, adjustedTime);
        double shift;
        if (isPromotionAscendant)
            promotionCoefficient = 0;
        else
            promotionCoefficient = 1;

        while (currentPromotionValue <= averagePromotionValue) {
            if (isPromotionAscendant)
                shift = promotionCoefficient += 0.05;
            else
                shift = promotionCoefficient -= 0.05;

            currentPromotionValue = getPromotionValues(startDate, startValue, desiredValue, finishDate, stepOfPromotion, shift, adjustedTime).getLast().getPromotionValue();
        }
        return Double.parseDouble(decimalFormat.format(promotionCoefficient));
    }

    @Override
    public double getLeftEdge(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime) {
        return getPromotionValues(startDate, startValue, desiredValue, finishDate, stepOfPromotion, 0, adjustedTime).getLast().getPromotionValue();
    }

    @Override
    public double getRightEdge(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime) {
        return getPromotionValues(startDate, startValue, desiredValue, finishDate, stepOfPromotion, 1, adjustedTime).getLast().getPromotionValue();
    }

    @Override
    public boolean getIsPromotionAscendant(double startDate, double startValue, double desiredValue, double finishDate, double stepOfPromotion, double adjustedTime) {
        double rightEdge =  getRightEdge(startDate, startValue, desiredValue, finishDate, stepOfPromotion, adjustedTime);
        return getPromotionValues(startDate, startValue, desiredValue, finishDate, stepOfPromotion, 0.05, adjustedTime).getLast().getPromotionValue() < rightEdge;
    }

    @Override
    public boolean isAutoCalculatedPromotion(Double coefficient) {
        boolean autoCalculatedPromotion = true;
        if (coefficient != null)
            autoCalculatedPromotion = false;
        return autoCalculatedPromotion;
    }

    @Override
    @Transactional
    public double getAutoCalculatedPromotionCoefficient(String universityName, String promotionCriterion, double startDate, double targetDate, double promotionStep) {
        int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
        int criterionId = universityPromotionDao.getCriterionIdByCriterionName(promotionCriterion);
        return universityPromotionDao.getAutocalculatedPromotionCoefficient(universityId, criterionId, startDate, targetDate, promotionStep);
    }

    @Override
    @Transactional
    public void deletePromotionDataByUniIAndCriterion(String universityName, String promotionCriterion) {
        int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
        int criterionId = universityPromotionDao.getCriterionIdByCriterionName(promotionCriterion);
        universityPromotionDao.deletePromotionDataByIds(universityId, criterionId);
    }

    @Override
    @Transactional
    public int getUniversityPromotionLaunces(String universityName, String promotionCriterion) {
        int universityId = universityPromotionDao.getUniversityIdByUniversityName(universityName);
        int criterionId = universityPromotionDao.getCriterionIdByCriterionName(promotionCriterion);
        return universityPromotionDao.getNumberOfUniversityPromotionLaunches(universityId, criterionId);
    }

    public double getFinalPromotionCoefficient() {
        return finalPromotionCoefficient;
    }

    public void setFinalPromotionCoefficient(double finalPromotionCoefficient) {
        this.finalPromotionCoefficient = finalPromotionCoefficient;
    }
}
