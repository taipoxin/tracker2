package by.tiranid.old;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
}
