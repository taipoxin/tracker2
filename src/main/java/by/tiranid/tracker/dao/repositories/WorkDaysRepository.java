package by.tiranid.tracker.dao.repositories;

import by.tiranid.tracker.dao.model.WorkDaysEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;


public interface WorkDaysRepository extends CrudRepository<WorkDaysEntity, Long> {


	


    WorkDaysEntity findFirstById(Long id);
    WorkDaysEntity findFirstByWorkDate(Date workDate);
    WorkDaysEntity findFirstByIterations(Byte iterations);
    WorkDaysEntity findFirstByWorkTime(String workTime);



    List<WorkDaysEntity> findByWorkDate(Date workDate);
    List<WorkDaysEntity> findByIterations(Byte iterations);
    
	// поиск по workDate в промежутке от до и просто от и просто до
	// + упорядочивание по iterations в обратном порядке
	
	
	
    List<WorkDaysEntity> findAll();


}
