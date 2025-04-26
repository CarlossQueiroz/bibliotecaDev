package br.com.bibliotecaDev.servicos;

import java.util.List;

public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String publisher;
    private String description;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }
}
