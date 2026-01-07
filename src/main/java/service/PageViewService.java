package service;

import dao.PageViewRepository;
import model.PageView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PageViewService {
    private final PageViewRepository repository;

    public PageViewService(PageViewRepository repository) {
        this.repository = repository;
    }


    public int incrementPageView(PageView req) {
        LocalDate today = LocalDate.now();
        PageView pageView = repository.findByPageNameAndDate(req.getPageName(), today)
            .orElseGet(() -> {
                PageView pv = new PageView();
                pv.setPageName(req.getPageName());
                pv.setDate(today);
                pv.setViewCount(0);
                return pv;
            });
        // Always update mobile before saving
        pageView.setMobile(req.getMobile());
        pageView.setViewCount(pageView.getViewCount() + 1);
        repository.save(pageView);
        return pageView.getViewCount();
    }
}