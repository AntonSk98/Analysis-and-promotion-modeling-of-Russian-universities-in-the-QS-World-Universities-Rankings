package models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "russian_universities_in_QS")
public class RussianUniversitiesInQS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rank_2019")
    private int rank2019;
    @Column(name = "rank_2020")
    private int rank2020;
    @Column(name = "institution_name")
    private String institutionName;
    @OneToMany(mappedBy = "russianUniversitiesInQS", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityClassification> universityClassifications;
    @OneToMany(mappedBy = "russianUniversitiesInQS", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityCriteria> universityCriteria;
    @OneToMany(mappedBy = "russianUniversitiesInQS", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FundamentalUniversityDataInQS> fundamentalUniversityDataInQS;
    @OneToMany(mappedBy = "russianUniversitiesInQS", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityModelingPromotion> universityModelingPromotions;

    public int getId() {
        return id;
    }

    public float getRank2019() {
        return rank2019;
    }

    public float getRank2020() {
        return rank2020;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRank2019(int rank_2019) {
        this.rank2019 = rank_2019;
    }

    public void setRank2020(int rank_2020) {
        this.rank2020 = rank_2020;
    }

    public void setInstitutionName(String institution_name) {
        this.institutionName = institution_name;
    }

    public Set<UniversityClassification> getUniversityClassifications() {
        return universityClassifications;
    }

    public void setUniversityClassifications(Set<UniversityClassification> UniversityClassifications) {
        this.universityClassifications = UniversityClassifications;
    }

    public Set<UniversityCriteria> getUniversityCriteria() {
        return universityCriteria;
    }

    public void setUniversityCriteria(Set<UniversityCriteria> universityCriteria) {
        this.universityCriteria = universityCriteria;
    }

    public Set<FundamentalUniversityDataInQS> getFundamentalUniversityDataInQS() {
        return fundamentalUniversityDataInQS;
    }

    public void setFundamentalUniversityDataInQS(Set<FundamentalUniversityDataInQS> fundamentalUniversityDataInQS) {
        this.fundamentalUniversityDataInQS = fundamentalUniversityDataInQS;
    }

    public Set<UniversityModelingPromotion> getUniversityModelingPromotions() {
        return universityModelingPromotions;
    }

    public void setUniversityModelingPromotions(Set<UniversityModelingPromotion> universityModelingPromotions) {
        this.universityModelingPromotions = universityModelingPromotions;
    }
}
