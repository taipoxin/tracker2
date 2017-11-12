package by.tiranid.tracker;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.MultipartConfigElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class AppConfig {

    private static boolean enableTimer = false;

    // start scheduling part

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    /**
     * fixedRate - интервал между вызовами начиная с начала работы
     * fixedDelay - интервал между вызовами начиная с окончания работы
     * show time every 5 seconds
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        if (enableTimer) {
            log.info("The time is now: " + dateFormat.format(new Date()));
        }

    }

    // end scheduling part


    // соответствует <multipart-config> в web.xml
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }

    public static final String message = "<h1>Микроклимат \n" +
            "\n" +
            "метеорологические условия и микроклимат помещений должны соответствовать госту \"Общие сан-гигиен требования к воздуху рабочей зоны\"\n" +
            "\n" +
            "СНБ (сан нормы) - нормативный документ\n" +
            "\"Отопление, вентиляция и кондиционир. воздуха\"\n" +
            "\n" +
            "\n" +
            "\n" +
            "Вентиляция:\n" +
            "\n" +
            "\tЕстественная\n" +
            "\tИскусственная (вытяжная местная общая)\n" +
            "\t\tПриточная\n" +
            "\tСмешанная(приточно - вытяжная)\n" + "</h1>";

    public static void main(String[] args){
        SpringApplication.run(AppConfig.class);
    }

}
