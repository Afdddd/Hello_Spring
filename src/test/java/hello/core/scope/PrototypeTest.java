package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeTestConfig.class);
        System.out.println("find prototypeBean1");
        prototypeTestConfig prototypeBean1 = ac.getBean(prototypeTestConfig.class);
        System.out.println("find prototypeBean2");
        prototypeTestConfig prototypeBean2 = ac.getBean(prototypeTestConfig.class);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        System.out.println(prototypeBean1.getClass());
        System.out.println(prototypeBean2.getClass());

        ac.close();
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }




    @Scope("prototype")
    static class prototypeTestConfig{

        @PostConstruct
        public void init() {
            System.out.println("prototypeTestConfig.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeTestConfig.destroy");
        }
    }
}
