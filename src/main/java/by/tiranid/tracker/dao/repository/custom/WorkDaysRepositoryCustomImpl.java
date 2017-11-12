package by.tiranid.tracker.dao.repository.custom;

import by.tiranid.tracker.dao.model.WorkDaysEntity;
import by.tiranid.tracker.dao.repository.WorkDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkDaysRepositoryCustomImpl implements WorkDaysRepositoryCustom {

    private WorkDaysRepository WorkDaysRepository;

    @Autowired
    public WorkDaysRepositoryCustomImpl(WorkDaysRepository WorkDaysRepository) {
        this.WorkDaysRepository = WorkDaysRepository;
    }


    // поиск по workDate в промежутке от до и просто от и просто до
    // + упорядочивание по iterations в обратном порядке


    public WorkDaysEntity getFirst() {
        return WorkDaysRepository.findAll().get(0);
    }


    public WorkDaysEntity getLast() {
        List<WorkDaysEntity> list = WorkDaysRepository.findAll();
        return list.get(list.size() - 1);
    }

}
