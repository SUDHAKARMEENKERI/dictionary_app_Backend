package controller;

import model.PageView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PageViewService;

import java.util.Map;

@RestController
@RequestMapping("/api/page-view")
@CrossOrigin("*")
public class PageViewController {
    private final PageViewService service;

    public PageViewController(PageViewService service) {
        this.service = service;
    }

    @PostMapping("/increment")
    public ResponseEntity<Map<String, Object>> incrementPageView(@RequestBody PageView req) {
        int count = service.incrementPageView(req);
        return ResponseEntity.ok(Map.of(
                "pageName", req.getPageName(),
                "viewCount", count
        ));
    }
}