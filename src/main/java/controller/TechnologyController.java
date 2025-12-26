package controller;

import model.TechnologyCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TechnologyService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TechnologyController {

    @Autowired
    private TechnologyService service;

    @GetMapping("/technologies")
    public List<TechnologyCategoryDto> getTechnologies() {
        return service.getTechnologyForUi();
    }

}
