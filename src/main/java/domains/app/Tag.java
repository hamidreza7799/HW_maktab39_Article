package domains.app;

import base.domains.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag extends BaseEntity<Integer> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String title;
    @ManyToMany(mappedBy = "articleTags")
    private Set<Article> tagArticles = new HashSet<>();
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Article> getTagArticles() {
        return tagArticles;
    }

    public void setTagArticles(Set<Article> tagArticles) {
        this.tagArticles = tagArticles;
    }
}
