package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototype {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void SingletonWithPrototypeTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientSingleton.class);
        ClientSingleton Client1 = ac.getBean(ClientSingleton.class);
        int ClientCount1 = Client1.logic();

        ClientSingleton Client2 = ac.getBean(ClientSingleton.class);
        int ClientCount2 = Client2.logic();

        assertThat(ClientCount1).isEqualTo(1);
        assertThat(ClientCount2).isEqualTo(2);



    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Scope("singleton")
    static class ClientSingleton{
        private final PrototypeBean PrototypeBean;

        @Autowired
        public ClientSingleton(PrototypeBean PrototypeBean) {
            this.PrototypeBean = PrototypeBean;
        }

        public int logic() {
            PrototypeBean.addCount();
            int count = PrototypeBean.getCount();
            return count;
        }

    }
}
