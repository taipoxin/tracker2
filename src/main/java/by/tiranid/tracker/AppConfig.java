package by.tiranid.tracker;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class AppConfig {


    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String server_Ip = addr.getHostAddress();
        String server_port = "8080";
        String[] ipArg = {"--server.address=" + server_Ip, "--server.port=" + server_port};


        log.info("\n\n" + "Loading on " + server_Ip + ":" + server_port + "\n");
        SpringApplication.run(AppConfig.class, ipArg);
    }

}
