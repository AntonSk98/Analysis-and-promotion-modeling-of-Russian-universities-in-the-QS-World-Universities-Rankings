package dao;

import entities.RussianUniversitiesInQS;
import entities.UniversityClassification;

import java.util.List;


public interface RuissianUniversitiesDAO {
    List<RussianUniversitiesInQS> getAllTopRussianUniversititesInQS();

    List<UniversityClassification> getClassificationById(int university_id);
}
