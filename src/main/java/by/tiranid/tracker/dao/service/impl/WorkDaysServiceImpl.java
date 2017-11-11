package by.tiranid.tracker.dao.service.impl;

import by.tiranid.tracker.dao.model.WorkDaysEntity;
import by.tiranid.tracker.dao.repositories.WorkDaysRepository;
import by.tiranid.tracker.dao.service.WorkDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class WorkDaysServiceImpl implements WorkDaysService {

    private WorkDaysRepository WorkDaysRepository;

    @Autowired
    public WorkDaysServiceImpl(WorkDaysRepository WorkDaysRepository) {
        this.WorkDaysRepository = WorkDaysRepository;
    }

	
	// поиск по workDate в промежутке от до и просто от и просто до
	// + упорядочивание по iterations в обратном порядке
   
	

    
    public WorkDaysEntity addRecord(WorkDaysEntity record) {
        return WorkDaysRepository.save(record);
    }

    
    public void delete(Long id) {
        WorkDaysRepository.delete(id);
    }

    
    public WorkDaysEntity editRecord(WorkDaysEntity record) {
        // hibernate will update it
        return WorkDaysRepository.save(record);
    }


    public List<WorkDaysEntity> getAll() {
        return WorkDaysRepository.findAll();
    }
	
    
    public WorkDaysEntity getFirstById(Long id) {
        return WorkDaysRepository.findFirstById(id);
    }

    
    public WorkDaysEntity getFirstByWorkDate(Date workDate) {
        return WorkDaysRepository.findFirstByWorkDate(workDate);
    }

    
    public WorkDaysEntity getFirstByIterations(Byte iterations) {
        return WorkDaysRepository.findFirstByIterations(iterations);
    }

	
	public WorkDaysEntity getFirstByWorkTime(String workTime) {
        return WorkDaysRepository.findFirstByWorkTime(workTime);
    }
	

    
    public List<WorkDaysEntity> getByWorkDate(Date workDate) {
        return WorkDaysRepository.findByWorkDate(workDate);
    }
	
	public List<WorkDaysEntity> getByIterations(Byte iterations) {
        return WorkDaysRepository.findByIterations(iterations);
    }
    


    public WorkDaysEntity getFirst() {
        return WorkDaysRepository.findAll().get(0);
    }

    
    public WorkDaysEntity getLast() {
        List<WorkDaysEntity> list = WorkDaysRepository.findAll();
        return list.get(list.size()-1);
    }

}
