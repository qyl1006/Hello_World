package cn.wolfcode._01hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppConfig {
    public static void main(String[] args){
        System.out.println(args);
        SpringApplication.run(AppConfig.class, args);
    }

}
