/* should be executed once */
/*
    составной индекс по дате и итерациям
*/
ALTER TABLE work_days ADD INDEX work_date_iterations(work_date, iterations);

/*
      индекс по дате и длительности
*/
ALTER TABLE work_iters
  ADD INDEX ddate_duration(ddate, duration);
