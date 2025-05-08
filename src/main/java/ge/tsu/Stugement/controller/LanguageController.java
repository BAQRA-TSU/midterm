package ge.tsu.Stugement.controller;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/lang")
public class LanguageController {

    private final MessageSource messageSource;

    public LanguageController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/{lang}")
    public Map<String, String> getTranslations(@PathVariable String lang) {
        Locale locale = new Locale(lang);
        Map<String, String> translations = new HashMap<>();
        translations.put("page.title", messageSource.getMessage("page.title", null, locale));
        translations.put("add.student", messageSource.getMessage("add.student", null, locale));
        return translations;
    }
}
