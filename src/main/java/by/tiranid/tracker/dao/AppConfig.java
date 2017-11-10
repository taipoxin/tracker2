package by.tiranid.tracker.dao;

import by.tiranid.tracker.dao.config.DataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(DataConfig.class)
@SpringBootApplication
@ComponentScan("by.tiranid.tracker.dao")
public class AppConfig {

    public static void main(String[] args){
        SpringApplication.run(AppConfig.class);

    }

}
