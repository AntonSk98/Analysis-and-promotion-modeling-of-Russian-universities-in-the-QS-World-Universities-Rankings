package entities;

import javax.persistence.*;

@Entity(name = "university_criteria")
public class UniversityCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "score_rank_2019")
    private double scoreRank2019;
    @Column(name = "score_rank_2020")
    private double scoreRank2020;
    @ManyToOne()
    @JoinColumn(name = "criterion_id", referencedColumnName = "id")
    private CriteriaTableInQS criteriaTable;
    @ManyToOne()
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private RussianUniversitiesInQS russianUniversitiesInQS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScoreRank2019() {
        return scoreRank2019;
    }

    public void setScoreRank2019(double scoreRank2019) {
        this.scoreRank2019 = scoreRank2019;
    }

    public double getScoreRank2020() {
        return scoreRank2020;
    }

    public void setScoreRank2020(double scoreRank2020) {
        this.scoreRank2020 = scoreRank2020;
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
