package domains;

import base.domains.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Article extends BaseEntity<Integer> {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String brief;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "create_date") @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "last_update_date") @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Column(name = "is_published" ,columnDefinition = "boolean default false")
    private boolean isPublished;
    @ManyToOne(optional = false)
    private User creator;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(nullable = false)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tag> articleTags = new HashSet<>();

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<Tag> articleTags) {
        this.articleTags = articleTags;
    }

    @PrePersist
    public void initialDates() {
        this.createDate = new Date();
        this.lastUpdateDate = new Date();
    }

    @PreUpdate
    public void updateDates() {
        this.lastUpdateDate = new Date();
    }
}
