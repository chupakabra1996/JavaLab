package ru.kpfu.itis.service.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.model.RepositoryMapping;
import ru.kpfu.itis.service.GitHubApiService;

import java.util.Optional;

/**
 * Created by Safin Ramil on 24.04.17
 * Safin.Ramil@ordotrans.ru
 */

@Service
public class SearchReposGitHubApiService implements GitHubApiService {

    private static final Logger logger = LoggerFactory.getLogger(SearchReposGitHubApiService.class);

    private final RestTemplate template;

    private static final String QUERY_PARAMS_TEMPLATE = "?sort=stars&order=desc&q=language:java&page=%d";

    private static final String BASE_URL = "https://api.github.com/search/repositories";


    @Autowired
    public SearchReposGitHubApiService(
            @Qualifier("gitHubSearchReposRestTemplate") RestTemplate template
    ) {
        this.template = template;
    }


    @Override
    public Optional<RepositoryMapping> searchRepos(long page) {

        try {
            ResponseEntity<RepositoryMapping> resp =
                    template.getForEntity(BASE_URL + String.format(QUERY_PARAMS_TEMPLATE, page),
                            RepositoryMapping.class);

            if (resp.hasBody()) {
                return Optional.of(resp.getBody());

            } else {
                logger.warn("Response hasn't got body");
                return Optional.empty();
            }

        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);

            //FIXME: throw own exceptions
            return Optional.empty();
        }
    }

}
