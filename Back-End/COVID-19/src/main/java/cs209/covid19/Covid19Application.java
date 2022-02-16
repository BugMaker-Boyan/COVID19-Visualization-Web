package cs209.covid19;

import cs209.covid19.utils.LocalDataUtil;
import cs209.covid19.utils.OnlineDataUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Covid19Application {

    public static void main(String[] args) {
        LocalDataUtil.loadOWIDData();
        LocalDataUtil.loadWHOData();
        OnlineDataUtil.loadOWIDData();
        OnlineDataUtil.loadWHOData();
        SpringApplication.run(Covid19Application.class, args);
    }

}
