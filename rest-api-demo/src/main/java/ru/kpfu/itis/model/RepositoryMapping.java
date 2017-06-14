package ru.kpfu.itis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Safin Ramil on 24.04.17
 * Safin.Ramil@ordotrans.ru
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryMapping implements Serializable {

    @JsonProperty("total_count")
    private long totalCount;

    private List<Items> items;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class Items implements Serializable {

        private Long id;

        @JsonProperty("full_name")
        private String fullName;

        @JsonProperty("forks_count")
        private Long forksCount;

        @JsonProperty("stargazers_count")
        private Long starsCount;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Long getForksCount() {
            return forksCount;
        }

        public void setForksCount(Long forksCount) {
            this.forksCount = forksCount;
        }

        public Long getStarsCount() {
            return starsCount;
        }

        public void setStarsCount(Long starsCount) {
            this.starsCount = starsCount;
        }

        @Override
        public String toString() {
            return String.format("id: %d, fullName: %s, forks: %d, stars: %d",
                    id, fullName, forksCount, starsCount);
        }
    }
}
