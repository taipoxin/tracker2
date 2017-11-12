package by.tiranid.tracker.dao.repository.custom;


import by.tiranid.tracker.dao.model.WorkItersEntity;


public interface WorkItersRepositoryCustom {

    WorkItersEntity getLast();

    WorkItersEntity getFirst();

    // поиск по ddate в промежутке от до и просто от и просто до

}
