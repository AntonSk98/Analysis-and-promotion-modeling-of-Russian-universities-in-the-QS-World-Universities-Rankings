package services;

import dao.RuissianUniversitiesDAO;
import entities.RussianUniversitiesInQS;
import entities.UniversityClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class RussianUniversitiesServiceImplementation implements RussianUniversititesService {

    @Autowired
    private RuissianUniversitiesDAO russianUniversititesDAOImplementation;

    @Override
    @Transactional
    public List<RussianUniversitiesInQS> getAllRussianTopUniversities() {
        return russianUniversititesDAOImplementation.getAllTopRussianUniversititesInQS();
    }

    @Override
    @Transactional
    public List<UniversityClassification> getUniversityClassificationById(int university_id) {
        return russianUniversititesDAOImplementation.getClassificationById(university_id);
    }
}
