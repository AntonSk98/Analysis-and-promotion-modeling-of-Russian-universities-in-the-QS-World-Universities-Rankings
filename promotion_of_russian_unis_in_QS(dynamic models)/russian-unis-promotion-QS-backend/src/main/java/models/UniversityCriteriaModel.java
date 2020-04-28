package models;

public class UniversityCriteriaModel {
    private int id;
    private String universityName;
    private String criterionName;
    private float rank2019;
    private float rank2020;

    public UniversityCriteriaModel(
            int id, String universityName,
            String criterionName,
            float rank2019,
            float rank2020
    ) {
        this.id = id;
        this.universityName = universityName;
        this.criterionName = criterionName;
        this.rank2019 = rank2019;
        this.rank2020 = rank2020;
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

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public float getRank2019() {
        return rank2019;
    }

    public void setRank2019(float rank2019) {
        this.rank2019 = rank2019;
    }

    public float getRank2020() {
        return rank2020;
    }

    public void setRank2020(float rank2020) {
        this.rank2020 = rank2020;
    }
}
