package entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "criteria_table_in_QS")
public class CriteriaTableInQS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "criterion_name")
    private String criterionName;
    @OneToMany(mappedBy = "criteriaTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityCriteria> criteriaTableInQS;
    @OneToMany(mappedBy = "criteriaTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityModelingPromotion> universityModelingPromotion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public Set<UniversityCriteria> getCriteriaTableInQS() {
        return criteriaTableInQS;
    }

    public void setCriteriaTableInQS(Set<UniversityCriteria> criteriaTableInQS) {
        this.criteriaTableInQS = criteriaTableInQS;
    }

    public Set<UniversityModelingPromotion> getUniversityModelingPromotion() {
        return universityModelingPromotion;
    }

    public void setUniversityModelingPromotion(Set<UniversityModelingPromotion> universityModelingPromotion) {
        this.universityModelingPromotion = universityModelingPromotion;
    }
}
