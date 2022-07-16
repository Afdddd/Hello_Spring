package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //지역변수이기 때문에 값이 변할리 없다.
        int userA_price = statefulService1.order("userA", 10000);
        int userB_price = statefulService2.order("userB", 20000);

        System.out.println("userA_price = " + userA_price);

    }

    static class testConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }
}