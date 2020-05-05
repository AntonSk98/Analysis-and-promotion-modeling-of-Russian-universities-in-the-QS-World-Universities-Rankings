package models;

public class UniversityCriterionModel {
    private int id;
    private String universityName;
    private String criterionName;
    private double rank2019;
    private double rank2020;

    public UniversityCriterionModel(
            int id,
            String universityName,
            String criterionName,
            double rank2019,
            double rank2020
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

    public double getRank2019() {
        return rank2019;
    }

    public void setRank2019(double rank2019) {
        this.rank2019 = rank2019;
    }

    public double getRank2020() {
        return rank2020;
    }

    public void setRank2020(double rank2020) {
        this.rank2020 = rank2020;
    }
}
