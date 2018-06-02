package com.adesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        AdeskApplication.class,
        Jsr310JpaConverters.class
})
public class AdeskApplication {

    @PostConstruct
    void init()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(AdeskApplication.class, args);
    }
}
