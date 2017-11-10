package by.tiranid.tracker.container;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;


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
            "\tСмешанная(приточно - вытяжная)\n" +
            "\t\n" +
            "\tПо зоне обслуживания:\n" +
            "\t\tместные\n" +
            "\t\tобщие\n" +
            "\t\t\n" +
            "\tпо конструкции: \n" +
            "\t\tканальные\n" +
            "\t\tбесканальные\n" +
            "\t\t\n" +
            "\t\n" +
            "Влажность измеряется гигрометром психометрическим (как температуру, так и влажность можно)\n" +
            "\n" +
            "\t\n" +
            "\t\n" +
            "Виды освещения:\n" +
            "\n" +
            "Естественное (боковое, верхнее)\n" +
            "\n" +
            "Искусственная (Местное, общая, локальное)\n" +
            "\n" +
            "Измеряется люксометром (в люксах)\n" +
            "\n" +
            "\n</h1>";

    public static void main(String[] args){
        SpringApplication.run(AppConfig.class);
    }
}
