package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    public void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("find singletonBean");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();

    }

    //singleton 스코프가 default
    @Scope("singleton")
    static class SingletonBean{

        @PostConstruct //초기화 콜백 : 빈 생성되고, 빈 의존관계 주입 완료 후 호출
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy // 소멸전 콜백 : 빈 소멸직전에 호출
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
