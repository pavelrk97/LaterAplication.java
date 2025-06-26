package ru.practicum;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class LaterAplication {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        // connector — это компонент, который отвечает за «сеть»
        tomcat.getConnector().setPort(PORT);

        // то самое «приложение» или «контекст» с пустым путём
        Context tomcatContext = tomcat.addContext("", null);

        // класс Wrapper позволяет задать дополнительные настройки для сервлета
        /*ApplicationContext — указывает, что этот класс представляет реализацию контекста Spring-приложения
        (реализует интерфейс ApplicationContext).
        Web — сообщает, что это контекст именно веб-приложения. Он дополнительно
        расширяет интерфейс WebApplicationContext, который добавляет метод getServletContext — он важен
        для регистрации сервлетов.
        AnnotationConfig — говорит, что контекст будет работать с бинами и конфигурацией, которые отмечены
        специальными аннотациями, например @Component и @Controller. Альтернативой мог бы быть контекст,
        в котором все бины указывают в xml-файле — такой способ использовался раньше (тогда бы название
        класса начиналось на Xml — XmlWebApplicationContext).
        */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setServletContext(tomcatContext.getServletContext());
        applicationContext.scan("ru.practicum");
        applicationContext.refresh();
        /*Вызов метода .scan("ru.practicum") значит, что контекст будет искать бины в пакете ru.practicum.
        Метод .setServletContext передаёт контексту приложения, как и следует из названия, контекст
        сервлетов — ServletContext. Метод .refresh загружает Spring-контекст. Готово!
        */


        // добавляем диспетчер запросов
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Wrapper dispatcherWrapper =
                Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        dispatcherWrapper.addMapping("/");
        dispatcherWrapper.setLoadOnStartup(1);

        tomcat.start();
    }
}