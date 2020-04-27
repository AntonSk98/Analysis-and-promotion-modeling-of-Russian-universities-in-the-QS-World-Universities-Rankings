package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "classification_table_in_QS")
public class ClassificationTableInQS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "classification_name")
    private String classificationName;
    @JsonIgnore
    @OneToMany(mappedBy = "classificationTable", cascade = CascadeType.ALL)
    private Set<UniversityClassification> universityClassifications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public Set<UniversityClassification> getUniversityClassifications() {
        return universityClassifications;
    }

    public void setUniversityClassifications(Set<UniversityClassification> UniversityClassifications) {
        this.universityClassifications = UniversityClassifications;
    }
}
