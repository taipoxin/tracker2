package by.tiranid.tracker.dao.repository.custom;


import by.tiranid.tracker.dao.model.WorkDaysEntity;


public interface WorkDaysRepositoryCustom {

    WorkDaysEntity getLast();

    WorkDaysEntity getFirst();


    // поиск по workDate в промежутке от до и просто от и просто до
    // + упорядочивание по iterations в обратном порядке


}