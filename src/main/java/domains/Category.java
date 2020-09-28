package domains;

import base.domains.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseEntity<Integer> {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String title;
    @Lob
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Article> articleSet = new HashSet<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }
}
