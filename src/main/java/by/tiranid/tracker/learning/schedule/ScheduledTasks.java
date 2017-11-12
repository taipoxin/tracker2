package by.tiranid.tracker.learning.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;


@EnableScheduling
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    /**
     * fixedRate - интервал между вызовами начиная с начала работы
     * fixedDelay - интервал между вызовами начиная с окончания работы
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now" + dateFormat.format(new Date()));

    }

}