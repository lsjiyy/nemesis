# nemesis
访商城 个人开发
框架 spring-cloud version Finchley.SR2 +redis +kafka +redisson分布式锁
	gateway+jwt 做了全局鉴权
	eureka-server 注册中心(目前不是集群)
	数据连接池使用了druid
	mysql数据库
	springboot的email+freemaker 发送邮箱
	使用了undertow WEB容器
	分布式事务 txlcn (正在接入)
	
简单秒杀实现 
	kafka+redisson分布式锁
	redis操作库存预减,缓存用户秒杀订单
	秒杀到用kafka发送消息到订单块,减db库存,下单,缓存秒杀订单到redis
	
	模拟测试 由于创建用户太麻烦 就使用单用户 省去缓存秒杀订单步骤
	使用jmeter 线程数100000 ramp-up 0秒 模拟抢购没有超卖现象
    
鉴权
	gateway+Jwt 进行全局的接口鉴权
	
权限
	后台用户分配角色,角色分配可操作接口
	
分布式事务
	txlcn 正在接入
	
redis问题
	总是关闭主机
	
