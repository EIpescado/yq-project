#### Morph:
##### 自动生成单表CURD代码,pojo,mapper,service,controller,与 [mybatis-plus](https://github.com/baomidou/mybatis-plus) 耦合

```java
Morph morph = new Morph();
morph.modelBuilder().setIdType("Long")
        .setAuthor("yq")
        .setBasePackage("pers.yurwisher.morph.xo")
        .setEntityClass("pers.yurwisher.morph.entity.Test")
        .setDescription("entity test ,for test");
morph.configBuilder().setServiceSuperClass("pers.yurwisher.morph.service.BaseService")
        .setSo(false)
        .setQoSuperClass("pers.yurwisher.morph.common.wrapper.PageQo")
        .setMapperSuperClass("pers.yurwisher.morph.common.CommonMapper")
        .setMapperLocation("mapper.morph")
        .setServiceImplSuperClass("pers.yurwisher.morph.service.impl.BaseServiceImpl")
        .setControllerSuperClass("pers.yurwisher.morph.controller.BaseController");
morph.wave();
```
