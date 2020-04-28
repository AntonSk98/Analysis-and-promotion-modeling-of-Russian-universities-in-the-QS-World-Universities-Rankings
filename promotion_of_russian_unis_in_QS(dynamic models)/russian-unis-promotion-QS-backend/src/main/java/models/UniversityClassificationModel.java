package models;

public class UniversityClassificationModel {
    private int id;
    private String universityName;
    private String classificationName;
    private String classificationType;

    public UniversityClassificationModel(
            int id, String universityName,
            String classificationName,
            String classificationType
    ) {
        this.id = id;
        this.universityName = universityName;
        this.classificationName = classificationName;
        this.classificationType = classificationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }
}
