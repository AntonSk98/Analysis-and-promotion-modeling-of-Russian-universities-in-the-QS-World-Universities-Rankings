package services;

import entities.RussianUniversitiesInQS;
import entities.UniversityClassification;

import java.util.List;

public interface RussianUniversititesService {
    List<RussianUniversitiesInQS> getAllRussianTopUniversities();

    List<UniversityClassification> getUniversityClassificationById(int university_id);
}
