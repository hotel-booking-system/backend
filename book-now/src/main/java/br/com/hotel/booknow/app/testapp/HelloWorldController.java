package br.com.hotel.booknow.app.testapp;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping
@AllArgsConstructor
public class HelloWorldController {

    private final MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Parameters
    // /users/{id}/todos/{id} => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Juliane

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    /**
     * <b>Internationalization - i18n</b>
     * <p>
     * Exemplos:
     * <p>
     * en: English = Good Morning <br> nl: Dutch = Goedemorgen <br> fr: French = Bonjour <br> de: Deutsch = Guten
     * Morgen
     *
     * @return a tradução
     */
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println("Locale: " + locale);
        return messageSource.getMessage("bedroom.type.required", null,
                "Default Message", locale);
    }

}
