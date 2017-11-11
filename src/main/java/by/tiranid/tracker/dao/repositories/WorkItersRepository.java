package by.tiranid.tracker.dao.repositories;

import by.tiranid.tracker.dao.model.WorkItersEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public interface WorkItersRepository extends CrudRepository<WorkItersEntity, Long> {


	


    WorkItersEntity findFirstById(Long id);
    WorkItersEntity findFirstByDdate(Date ddate);
    WorkItersEntity findFirstByTtime(Time ttime);

    WorkItersEntity findFirstByDuration(Time duration);




    List<WorkItersEntity> findByDdate(Date ddate);

    List<WorkItersEntity> findByDuration(Time duration);

	// поиск по ddate в промежутке от до и просто от и просто до


    List<WorkItersEntity> findAll();


}