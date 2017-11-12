package by.tiranid.tracker.dao.repository.custom;

import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repository.WorkItersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkItersRepositoryCustomImpl implements WorkItersRepositoryCustom {

    private WorkItersRepository WorkItersRepository;

    @Autowired
    public WorkItersRepositoryCustomImpl(WorkItersRepository WorkItersRepository) {
        this.WorkItersRepository = WorkItersRepository;
    }
/*
    @Autowired
    private EntityManager entityManager;

    public final List<WorkItersEntity> findByNameWithWeirdOrdering(String name) {
        Query query = query(where("name").is(name));
        query.sort().on("whatever", Order.ASC);
        return entityManager.find(query, WorkItersEntity.class);
    }
*/

    public WorkItersEntity getFirst() {
        return WorkItersRepository.findAll().get(0);
    }


    public WorkItersEntity getLast() {
        List<WorkItersEntity> list = WorkItersRepository.findAll();
        return list.get(list.size() - 1);
    }

}
