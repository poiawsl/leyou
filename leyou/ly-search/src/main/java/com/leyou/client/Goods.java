package com.leyou.client;


import common.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface Goods extends GoodsApi {
}
