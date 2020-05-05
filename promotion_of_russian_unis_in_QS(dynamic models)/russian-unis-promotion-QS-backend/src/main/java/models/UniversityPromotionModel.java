package models;

public class UniversityPromotionModel {
    private double year;
    private double promotionValue;

    public UniversityPromotionModel() {

    }

    public UniversityPromotionModel(double year, double promotionValue) {
        this.year = year;
        this.promotionValue = promotionValue;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public double getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(double promotionValue) {
        this.promotionValue = promotionValue;
    }
}
