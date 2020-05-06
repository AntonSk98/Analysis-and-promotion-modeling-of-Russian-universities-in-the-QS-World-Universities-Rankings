package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "university_modeling_promotion")
public class UniversityModelingPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "promotion_date")
    private LocalDateTime calculationDate;
    @Column(name = "promotion_coefficient")
    private double promotionCoefficient;
    @Column(name = "auto_calculated_coefficient")
    private boolean autoCalculatedPromotion;
    @Column(name = "start_date")
    private double startDate;
    @Column(name = "start_value")
    private  double startValue;
    @Column(name = "target_date")
    private double targetDate;
    @Column(name = "promotion_value")
    private double promotionValue;
    @Column(name = "promotion_step")
    private double promotionStep;
    @ManyToOne()
    @JoinColumn(name = "critetion_id", referencedColumnName = "id")
    private CriteriaTableInQS criteriaTable;
    @ManyToOne()
    @JoinColumn(name = "unviersity_id", referencedColumnName = "id")
    private RussianUniversitiesInQS russianUniversitiesInQS;

    public double getPromotionStep() {
        return promotionStep;
    }

    public void setPromotionStep(double promotionStep) {
        this.promotionStep = promotionStep;
    }

    public double getStartDate() {
        return startDate;
    }

    public void setStartDate(double initialDate) {
        this.startDate = initialDate;
    }

    public double getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(double targetDate) {
        this.targetDate = targetDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double initialValue) {
        this.startValue = initialValue;
    }

    public double getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(double promotionValue) {
        this.promotionValue = promotionValue;
    }

    public LocalDateTime getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(LocalDateTime promotionDate) {
        this.calculationDate = promotionDate;
    }

    public double getPromotionCoefficient() {
        return promotionCoefficient;
    }

    public void setPromotionCoefficient(double userPromotionCoefficient) {
        this.promotionCoefficient = userPromotionCoefficient;
    }

    public CriteriaTableInQS getCriteriaTable() {
        return criteriaTable;
    }

    public void setCriteriaTable(CriteriaTableInQS criteriaTable) {
        this.criteriaTable = criteriaTable;
    }

    public RussianUniversitiesInQS getRussianUniversitiesInQS() {
        return russianUniversitiesInQS;
    }

    public void setRussianUniversitiesInQS(RussianUniversitiesInQS russianUniversitiesInQS) {
        this.russianUniversitiesInQS = russianUniversitiesInQS;
    }

    public boolean isAutoCalculatedPromotion() {
        return autoCalculatedPromotion;
    }

    public void setAutoCalculatedPromotion(boolean autoCalculatedPromotion) {
        this.autoCalculatedPromotion = autoCalculatedPromotion;
    }
}
