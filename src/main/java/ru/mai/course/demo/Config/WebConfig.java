package ru.mai.course.demo.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Bean
        @Description("Thymeleaf template resolver serving HTML 5")
        public ClassLoaderTemplateResolver templateResolver() {

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

            templateResolver.setPrefix("/templates/");
            templateResolver.setCacheable(false);
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            templateResolver.setCharacterEncoding("UTF-8");

            return templateResolver;
        }

        @Bean
        @Description("Thymeleaf template engine with Spring integration")
        public SpringTemplateEngine templateEngine() {

            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());

            return templateEngine;
        }

        @Bean
        @Description("Thymeleaf view resolver")
        public ViewResolver viewResolver() {

            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

            viewResolver.setTemplateEngine(templateEngine());
            viewResolver.setCharacterEncoding("UTF-8");

            return viewResolver;
        }

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/clients").setViewName("clients");
            registry.addViewController("/static/css/main.css").setViewName("../static/css/main.css");
            registry.addViewController("/static/js/select.js").setViewName("../static/js/select.js");
        }

    }