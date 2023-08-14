package ru.dolzhenkoms.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dolzhenkoms.backend.entity.MedicTerm;
import ru.dolzhenkoms.backend.repository.MedicTermRepository;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class HomeController {

    private final MedicTermRepository medicTermRepository;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, world!";
    }
}
