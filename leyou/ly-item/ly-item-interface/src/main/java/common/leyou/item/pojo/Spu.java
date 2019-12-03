package common.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import com.leyou.item.pojo.Sku;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "tb_spu")
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1; // 1级分类
    private Long cid2; // 3级分类
    private Long cid3; // 3级分类
    private String title; // 标题
    private String subTitle; // 子标题
    private Boolean saleable; // 是否上架

    @JsonIgnore
    private Boolean valid; // 是否有效
    private Date createTime; // 创建时间
    @JsonIgnore
    private Date lastUpdateTime; // 最后修改时间

    @Transient
    String cname;
    @Transient
    private  String bname;

    @Transient
    private List<Sku> skus;


}
