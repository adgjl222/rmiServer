import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RMIServer {
    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        System.out.println("服务已启动");
    }
}

