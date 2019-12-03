package common.leyou.item.api;

import common.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BrandApi {


    @GetMapping("brand/{id}")
    Brand queryBrandById(@PathVariable Long id);

}
