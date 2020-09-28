package domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TableMetadata{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tbl_name" , nullable = false , unique = true)
    private String  tableName;
    @OneToMany(mappedBy = "tableMetadata")
    private Set<TablecolumnMetadata> tablecolumnMetadataSet = new HashSet<>();
    @Column(name = "tbl_def")
    private String tableDef;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<TablecolumnMetadata> getTablecolumnMetadataSet() {
        return tablecolumnMetadataSet;
    }

    public void setTablecolumnMetadataSet(Set<TablecolumnMetadata> tablecolumnMetadataSet) {
        this.tablecolumnMetadataSet = tablecolumnMetadataSet;
    }

    public String getTableDef() {
        return tableDef;
    }

    public void setTableDef(String tableDef) {
        this.tableDef = tableDef;
    }
}
