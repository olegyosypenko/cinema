package ua.training;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import ua.training.controller.MainServlet;
import ua.training.controller.filters.AuthFilter;
import ua.training.controller.filters.EncodingFilter;
import ua.training.controller.filters.LocalizationFilter;
import ua.training.controller.listeners.ContextListener;
import ua.training.controller.listeners.SessionListener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MainServlet(), "/servlet/*");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new EncodingFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean localizationFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new LocalizationFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new AuthFilter());
        filterRegistrationBean.setOrder(3);
        filterRegistrationBean.addUrlPatterns("/servlet/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean contextListener() {
        ServletListenerRegistrationBean listenerReg = new ServletListenerRegistrationBean(new ContextListener());
        return listenerReg;
    }

    @Bean
    public ServletListenerRegistrationBean sessionListener() {
        ServletListenerRegistrationBean listenerReg = new ServletListenerRegistrationBean(new SessionListener());
        return listenerReg;
    }

    @Data
    @Entity
    class Hall {
        @Id @GeneratedValue int id;
        int rows;
        int columns;

        private Hall() {

        }

        public Hall(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }
    }
}
