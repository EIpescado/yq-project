#### Morph:
##### 用于自动生成一些pojo,service,controller:

```java
Morph morph = new Morph();
morph.modelBuilder().setIdType("Long")
        .setAuthor("yq")
        .setBasePackage("pers.yurwisher.morph.xo")
        .setEntityClass("pers.yurwisher.morph.entity.Test")
        .setDescription("entity test ,for test");
morph.configBuilder().setServiceSuperClass("pers.yurwisher.morph.service.BaseService")
        .setServiceImplSuperClass("pers.yurwisher.morph.service.impl.BaseServiceImpl")
        .setControllerSuperClass("pers.yurwisher.morph.controller.BaseController");
morph.wave();
```
