package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.RepositoryMapping;

import java.util.Optional;

/**
 * Created by Safin Ramil on 24.04.17
 * Safin.Ramil@ordotrans.ru
 */

@Service
public interface GitHubApiService {

    Optional<RepositoryMapping> searchRepos(long page);

}
