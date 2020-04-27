package entities;

import javax.persistence.*;

@Entity(name = "university_classification")
public class UniversityClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "classification_type")
    private String classificationType;
    @ManyToOne()
    @JoinColumn(name = "classification_id")
    private ClassificationTableInQS classificationTable;
    @ManyToOne()
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }
}
