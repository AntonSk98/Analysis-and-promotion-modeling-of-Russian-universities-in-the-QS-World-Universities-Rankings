package services.allRussianUniversities;

import dao.topRussianUniversities.TopRussianUniversitiesDao;
import entities.RussianUniversitiesInQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TopRussianUniversitiesServiceImpl implements TopRussianUniversitiesService {

    @Autowired
    private TopRussianUniversitiesDao russianUniversititesDAOImplementation;

    @Override
    @Transactional
    public List<RussianUniversitiesInQS> getAllRussianTopUniversities() {
        return russianUniversititesDAOImplementation.getAllTopRussianUniversititesInQS();
    }
}
