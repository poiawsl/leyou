package common.leyou.item.pojo;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Long skuId;
    private Integer seckillStock;    //秒杀可用库存
    private Integer seckillTotal;    //以秒杀数量
    private Integer stock;    //正常库存


}
