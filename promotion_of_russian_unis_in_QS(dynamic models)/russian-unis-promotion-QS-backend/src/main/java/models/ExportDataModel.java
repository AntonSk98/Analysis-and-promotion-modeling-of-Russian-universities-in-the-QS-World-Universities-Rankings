package models;

public class ExportDataModel {
    private int universityId;
    private int criterionId;
    private String path;

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(int criterionId) {
        this.criterionId = criterionId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
