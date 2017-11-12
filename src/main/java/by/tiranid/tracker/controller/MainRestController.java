package by.tiranid.tracker.controller;

import by.tiranid.tracker.AppConfig;
import by.tiranid.tracker.dao.model.EntityUtils;
import by.tiranid.tracker.dao.model.Greeting;
import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repositories.WorkDaysRepository;
import by.tiranid.tracker.dao.repositories.WorkItersRepository;
import by.tiranid.tracker.dao.service.WorkDaysService;
import by.tiranid.tracker.dao.service.WorkItersService;
import by.tiranid.tracker.dao.service.impl.WorkItersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;


@RestController
@Slf4j
public class MainRestController {

    private static int userHash = -442696469;
    @Autowired
    private WorkDaysRepository workDaysRepository;
    private WorkDaysService workDaysService;

    @Autowired
    private WorkItersRepository workItersRepository;
    private WorkItersService workItersService;


    private AtomicLong counter = new AtomicLong();

    /**
     * return json view of Greeting
     *
     * @param name name for greeting
     * @return {"id":%counter%,"content":"Hello, %name%!"}
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format("Hello, %s!", name));
    }

    /**
     * shows all work_iters table
     *
     * @return strings with all work_iters rows
     */
    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public String getIters() {
        if (workItersService == null) {
            workItersService = new WorkItersServiceImpl(workItersRepository);
        }
        List<WorkItersEntity> l = workItersService.getAll();
        StringBuilder builder = new StringBuilder();
        for (WorkItersEntity ent : l) {
            builder.append(ent.toString());
            builder.append("\n");
        }

        return changeNtoBr(builder.toString());
    }

    /**
     * current local time (by user's PC time) converted to "hh:mm:ss" format
     * @return current local time in "hh:mm:ss" format
     */
    public String getCurrentTimeString() {
        LocalTime t = LocalTime.now();
        return t.getHour() + ":" + t.getMinute() + ":" + t.getSecond();
    }

    /**
     * put new work_iter entity to database and show all saved entities
     * @return strings with work_iters rows
     */
    @RequestMapping(value = {"/put"}, method = RequestMethod.GET)
    public String putIter() {
        if (workItersService == null) {
            workItersService = new WorkItersServiceImpl(workItersRepository);
        }
        try {
            String currentTime = getCurrentTimeString();
            workItersService.addRecord(EntityUtils.createTestWorkItersEntity("2017-11-11", currentTime, "00:25:00"));
        } catch (Exception e) {
        }
        List<WorkItersEntity> l = workItersService.getAll();
        StringBuilder builder = new StringBuilder();
        for (WorkItersEntity ent : l) {
            builder.append(ent.toString());
            builder.append("\n");
        }

        return changeNtoBr(builder.toString());
    }

    /**
     * post iterations to database
     * @param hash user hash (for auth)
     * @param time time in "hh:mm:ss" format
     * @return OK if successfully saved, otherwise - EXPECTATION FAILED
     */
    @RequestMapping(value = {"/postIter"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity.BodyBuilder postIter(@RequestParam String hash, @RequestParam String time) {
        log.info("retrieved hash: " + hash);
        log.info("retrieved time: " + time);
        if (Integer.valueOf(hash) == -442696469) {
            userHash = Integer.valueOf(hash);
            if (workItersService == null) {
                workItersService = new WorkItersServiceImpl(workItersRepository);
            }
            long t = Long.valueOf(time);
            WorkItersEntity entity = new WorkItersEntity();
            entity.setDdate(new Date(t));
            entity.setTtime(new Time(t));
            log.info("adding new entity: ");
            log.info(entity.getDdate().toString());
            log.info(entity.getTtime().toString());
            workItersService.addRecord(entity);
            return ResponseEntity.ok();
        } else {
            log.warn("sorry");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     *
     * @return just HTTP OK code
     */
    @RequestMapping(value = {"/postIter"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity.BodyBuilder getToPostIter() {
        log.info("get request to postIter");
        log.info("Returning OK Http Code");
        return ResponseEntity.ok();
    }

    private String changeNtoBr(String message) {
        return Pattern.compile("\n").matcher(message).replaceAll("<br/>");
    }

    /**
     * show html string
     * @return
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String mainHello() {
        return changeNtoBr(AppConfig.message);
    }


}