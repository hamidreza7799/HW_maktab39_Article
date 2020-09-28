package domains.metadata;

import base.domains.BaseEntity;

import javax.persistence.*;

@Entity
public class AppMetadata extends BaseEntity<Integer> {
    @Id
    private Integer id = 1;
    @Lob
    private String appInfo;
    @Lob
    private String creatorInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(String creatorInfo) {
        this.creatorInfo = creatorInfo;
    }
}
