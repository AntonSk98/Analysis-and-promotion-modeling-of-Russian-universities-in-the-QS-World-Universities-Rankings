package models;

import javax.persistence.*;

@Entity(name = "university_modeling_promotion")
public class UniversityModelingPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "efficient_promotion_coefficient")
    private String efficientPromotionCoefficient;
    @Column(name = "user_promotion_coefficient")
    private String userPromotionCoefficient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "critetion_id", referencedColumnName = "id")
    private CriteriaTableInQS criteriaTable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unviersity_id", referencedColumnName = "id")
    private RussianUniversitiesInQS russianUniversitiesInQS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEfficientPromotionCoefficient() {
        return efficientPromotionCoefficient;
    }

    public void setEfficientPromotionCoefficient(String efficientPromotionCoefficient) {
        this.efficientPromotionCoefficient = efficientPromotionCoefficient;
    }

    public String getUserPromotionCoefficient() {
        return userPromotionCoefficient;
    }

    public void setUserPromotionCoefficient(String userPromotionCoefficient) {
        this.userPromotionCoefficient = userPromotionCoefficient;
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
