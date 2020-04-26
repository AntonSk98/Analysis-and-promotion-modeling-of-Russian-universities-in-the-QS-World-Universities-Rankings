package models;

import javax.persistence.*;

@Entity(name = "university_classification")
public class UniversityClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "classification_type")
    private String classificationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id")
    private ClassificationTableInQS classificationTable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private RussianUniversitiesInQS russianUniversitiesInQS;

    public ClassificationTableInQS getClassificationTable() {
        return classificationTable;
    }

    public void setClassificationTable(ClassificationTableInQS classificationTable) {
        this.classificationTable = classificationTable;
    }

    public RussianUniversitiesInQS getRussianUniversitiesInQS() {
        return russianUniversitiesInQS;
    }

    public void setRussianUniversitiesInQS(RussianUniversitiesInQS russianUniversitiesInQS) {
        this.russianUniversitiesInQS = russianUniversitiesInQS;
    }
}
