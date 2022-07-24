package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonTestConfig.class);
        SingletonTestConfig singletonBean1 = ac.getBean(SingletonTestConfig.class);
        SingletonTestConfig singletonBean2 = ac.getBean(SingletonTestConfig.class);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        System.out.println(singletonBean1.getClass()); //CGLIB을 사용하지 않은 순수한 객체

        ac.close();
    }

    @Scope("singleton")
    static class SingletonTestConfig{

        @PostConstruct
        public void init() {
            System.out.println("SingletonTestConfig.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonTestConfig.destroy");
        }
    }
}
