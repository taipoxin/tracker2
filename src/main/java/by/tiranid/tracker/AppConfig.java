package by.tiranid.tracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("by.tiranid.tracker")
public class AppConfig {

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
