package by.tiranid.tracker.controller;

import by.tiranid.tracker.dao.model.EntityUtils;
import by.tiranid.tracker.dao.model.Greeting;
import by.tiranid.tracker.dao.model.WorkItersEntity;
import by.tiranid.tracker.dao.repository.WorkItersRepository;
import by.tiranid.tracker.dao.repository.custom.WorkItersRepositoryCustom;
import by.tiranid.tracker.learning.consuming_json.JsonPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



@RestController
@Slf4j
public class MainRestController {

    private static int userHash = -442696469;

    @Autowired
    private WorkItersRepository workItersRepository;

    private WorkItersRepositoryCustom workItersRepositoryCustom;

    private AtomicLong counter = new AtomicLong();

    private WorkItersEntity genEntityFromParams(String date, String time, String duration) {
        WorkItersEntity entity = new WorkItersEntity();
        // setting time
        Time cTime = RestUtils.convertStringToTime(time);
        log.info("Time param converted from " + time + " to " + (cTime != null ? cTime.toString() : null));
        entity.setTtime(cTime);

        // setting date
        Date cDate = RestUtils.convertStringToDate(date);
        log.info("Date param converted from " + date + " to " + (cDate != null ? cDate.toString() : null));
        entity.setDdate(cDate);

        // setting duration
        Time cDuration = RestUtils.convertStringToDurationTime(duration);
        log.info("Duration param converted from " + duration + " to " + (cDuration != null ? cDuration.toString() : null));
        entity.setDuration(cDuration);

        return entity;
    }

    /**
     * handle json
     *
     * @param hash, date, time, duration
     * @return OK/Bad request codes
     */
    @RequestMapping(value = {"/postIter"}, method = RequestMethod.POST)
    public @ResponseBody
    String postIterDirectly(
            @RequestParam String hash,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String duration) {

        return postIterByJsonBody(new IterationRequest(hash, date, time, duration));
    }

    /**
     * handle json
     *
     * @param iterationRequest body with params hash, date, time, duration
     * @return OK/Bad request codes
     */
    @RequestMapping(value = {"/postIter"}, method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String postIterByJsonBody(@RequestBody IterationRequest iterationRequest) {
        if (iterationRequest == null) {
            return null;
        }

        String date = iterationRequest.getDate();
        String time = iterationRequest.getTime();
        String duration = iterationRequest.getDuration();
        if (date.equals("")) date = null;
        if (time.equals("")) time = null;
        if (duration.equals("")) duration = null;

        if (Integer.valueOf(iterationRequest.getHash()) == userHash) {
            WorkItersEntity entity = genEntityFromParams(date, time, duration);
            workItersRepository.save(entity);
            return "Status code: 200 (OK)";
        }
        return "Status code : 400 (BAD_REQUEST)";
    }

    /**
     * @return just HTTP OK code
     */
    @RequestMapping(value = {"/postIter"}, method = RequestMethod.GET)
    public @ResponseBody
    String getToPostIter() {
        log.info("get request to postIter");
        log.info("Returning OK Http Code");
        return "Status code: 200 (OK)";
    }









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
        List<WorkItersEntity> l = workItersRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (WorkItersEntity ent : l) {
            builder.append(ent.toString());
            builder.append("\n");
        }

        return RestUtils.changeNtoBr(builder.toString());
    }

    /**
     * put new work_iter entity to database and show all saved entities
     * @return strings with work_iters rows
     */
    @RequestMapping(value = {"/put"}, method = RequestMethod.GET)
    public String putIter() {

        try {
            String currentTime = RestUtils.getCurrentTimeString();
            workItersRepository.save(EntityUtils.createTestWorkItersEntity("2017-11-11", currentTime, "00:25:00"));
        } catch (Exception e) {
        }
        List<WorkItersEntity> l = workItersRepository.findAll();
        StringBuilder builder = new StringBuilder();
        for (WorkItersEntity ent : l) {
            builder.append(ent.toString());
            builder.append("\n");
        }

        return RestUtils.changeNtoBr(builder.toString());
    }


}