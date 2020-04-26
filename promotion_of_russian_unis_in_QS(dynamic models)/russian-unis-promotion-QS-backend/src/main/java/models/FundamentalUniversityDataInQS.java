package models;

import javax.persistence.*;

@Entity(name = "fundamental_university_data_in_QS")
public class FundamentalUniversityDataInQS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "adjusted_promotion_coefficient")
    private String adjustedPromotionCoefficient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterion_id")
    private CriteriaTableInQS criteriaTable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private RussianUniversitiesInQS russianUniversitiesInQS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdjustedPromotionCoefficient() {
        return adjustedPromotionCoefficient;
    }

    public void setAdjustedPromotionCoefficient(String adjustedPromotionCoefficient) {
        this.adjustedPromotionCoefficient = adjustedPromotionCoefficient;
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
}
