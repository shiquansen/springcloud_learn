### 版本问题
- 一定要指定官方的,不然会出问题,这里用的2.2.7RELEASE

### 端口号
- 8070 nacos
- 8071 producer
- 8072 consumer
- 8073 feign

### nacos
- 验证config好不好使
- 验证注册中心好不好使

### consumer -> provider
- 都注册上去
- 使用serviceId+restTemplate能远程调用(`com.example.nacos.config.controller.EchoController.echo`)

### feign -> provider
- 使用feign注解能够注册上到nacos
- 能够通过feignclient远程调用provider接口(`com.example.nacos.config.controller.TransferController.transfer`)

### ribbon
- 配置 org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
- 负载均衡器
![img.png](filedir/nacos/img/img.png)


#### 带`依赖`打包 [链接](https://cloud.tencent.com/developer/article/1683810)