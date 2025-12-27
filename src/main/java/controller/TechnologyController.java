package controller;

import model.DropdownResponse;
import model.TechnologyCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getTechCategories")
    public List<DropdownResponse> categories() {
        return service.getCategories();
    }

    @GetMapping("/getTechItems")
    public List<DropdownResponse> items(@RequestParam Long category_id) {
        return service.getItems(category_id);
    }

}
