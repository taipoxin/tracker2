package by.tiranid.tracker.controller;

import by.tiranid.tracker.AppConfig;
import by.tiranid.tracker.dao.model.EntityUtils;
import by.tiranid.tracker.dao.model.Greeting;
import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repository.WorkDaysRepository;
import by.tiranid.tracker.dao.repository.WorkItersRepository;
import by.tiranid.tracker.dao.repository.custom.WorkDaysRepositoryCustom;
import by.tiranid.tracker.dao.repository.custom.WorkItersRepositoryCustom;
import by.tiranid.tracker.dao.repository.custom.WorkItersRepositoryCustomImpl;
import by.tiranid.tracker.learning.consuming_json.JsonPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private WorkDaysRepositoryCustom workDaysRepositoryCustom;

    @Autowired
    private WorkItersRepository workItersRepository;
    private WorkItersRepositoryCustom workItersRepositoryCustom;


    private AtomicLong counter = new AtomicLong();


    // file uploading

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

    @RequestMapping("/upFile")
    public ModelAndView upFileHTML() {
        return new ModelAndView("upload");
    }


    // json retrieving via objects

    /**
     * example of input : "static/json/test.json" is like (.../resources/static/json/test.json)
     *
     * @param innerFoldersPath path without start "/" inside "resource" project path
     * @return string with working path
     */
    public static String getPathFromResourcePath(String innerFoldersPath) {
        return ClassLoader.getSystemResource(innerFoldersPath).getFile();
    }


    /**
     * retrieve json file from resources
     *
     * @return json file in string format
     * @throws IOException
     */
    @RequestMapping(value = {"/jsonFromRes"}, method = RequestMethod.POST)
    public @ResponseBody
    String getJsonFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(getPathFromResourcePath("static/json/test.json"));
        Object json = mapper.readValue(file, Object.class);
        String s = mapper.writeValueAsString(json);
        return s;
    }

    @RequestMapping(value = {"/jsonFromRes"}, method = RequestMethod.GET)
    public @ResponseBody
    String getJsonFromString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String s = "{" +
                "\"id\": \"161112704050757\", " +
                "\"name\": \"Computer Services\"," +
                "\"phone\": \"(650) 286-8012\"," +
                "\"website\": \"http://www.gopivotal.com\"" +
                "}";
        return s;
    }


    /**
     * retrive json file with variables, existing in JsonPage
     * only if resource is retrived by GET method
     *
     * @return json object else - 500 code
     */
    @RequestMapping(value = {"/showJson"}, method = RequestMethod.GET)
    public JsonPage showJson() {
        RestTemplate restTemplate = new RestTemplate();
        // for get request:
        JsonPage page = restTemplate.getForObject("http://localhost:8080/jsonFromRes", JsonPage.class);
        // for post request:
        //JsonPage page = restTemplate.postForObject("http://localhost:8080/jsonFromRes", JsonPage.class);
        return page;
    }

    // object retrieving like json

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
        if (workItersRepositoryCustom == null) {
            workItersRepositoryCustom = new WorkItersRepositoryCustomImpl(workItersRepository);
        }
        List<WorkItersEntity> l = workItersRepository.findAll();
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

        try {
            String currentTime = getCurrentTimeString();
            workItersRepository.save(EntityUtils.createTestWorkItersEntity("2017-11-11", currentTime, "00:25:00"));
        } catch (Exception e) {
        }
        List<WorkItersEntity> l = workItersRepository.findAll();
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
            if (workItersRepositoryCustom == null) {
                workItersRepositoryCustom = new WorkItersRepositoryCustomImpl(workItersRepository);
            }
            long t = Long.valueOf(time);
            WorkItersEntity entity = new WorkItersEntity();
            entity.setDdate(new Date(t));
            entity.setTtime(new Time(t));
            log.info("adding new entity: ");
            log.info(entity.getDdate().toString());
            log.info(entity.getTtime().toString());
            workItersRepository.save(entity);
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