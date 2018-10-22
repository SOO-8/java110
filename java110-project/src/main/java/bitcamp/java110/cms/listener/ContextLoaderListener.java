package bitcamp.java110.cms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bitcamp.java110.cms.AppConfig;

//@WebListener (xml에 넣어줌)
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ContextLoaderListener.contextInitialized() 실행!");
        
        ServletContext sc = sce.getServletContext();

        try {
            
            AppConfig.sc = sce.getServletContext();
            ApplicationContext context = 
                    new AnnotationConfigApplicationContext(AppConfig.class);

            // iocContainer를 저장.
            sc.setAttribute("iocContainer", context);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
