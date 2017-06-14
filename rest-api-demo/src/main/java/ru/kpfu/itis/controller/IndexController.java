package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.service.GitHubApiService;

import java.util.Optional;

/**
 * Created by Safin Ramil on 24.04.17
 * Safin.Ramil@ordotrans.ru
 */

@Controller
public class IndexController {

    private final GitHubApiService gitHubApiService;

    private final Long DEFAULT_PAGE = 1L;

    @Autowired
    public IndexController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/")
    public String showIndex(
            @RequestParam("page") Optional<Long> page,
            ModelMap model
    ) {

        Long evalPage = page.orElse(DEFAULT_PAGE);

        gitHubApiService.searchRepos(evalPage).ifPresent(repos -> {
            model.addAttribute("repos", repos.getItems());
        });

        return "index";
    }
}
