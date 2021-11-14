# MapStruct学习笔记

## 一、背景

在日常开发的多模块多层级的项目中，应用与应用之间，模块与模块之间的数据模型一般都不通用，每层都有自己的数据模型。对于不同领域使用不同的JavaBean对象传输，避免互相影响，在不同层之间进行数据传输时，不可避免的需要将这些对象进行互相转化操作。

**常见的转化方式有**：

- 调用getter/setter方法进行属性赋值：一大堆‘巨简单’的代码，不美观，而且如果字段过多，容易眼花复制错字段。
- 调用BeanUtil.copyPropertie进行反射属性赋值：坑巨多，比如sources与target写反，难以定位某个字段在哪里进行的赋值，不利于debug，同时因为用到反射，导致性能也不佳。再者就是仅支持简单的浅拷贝。

> 而MapStruct则规避了这些问题，简单、性能好、支持各种拷贝。

## 二、简介

MapStruct是一个代码生成器，它基于约定优于配置的方法极大地简化了Java bean类型之间映射的实现。

您所要做的就是定义一个mapper接口，该接口声明任何所需的映射方法。在编译期间，MapStruct将生成此接口的实现。此实现使用普通的Java方法调用来在源对象和目标对象之间进行映射，即没有反射或类似。

与手工编写映射代码相比，MapStruct通过生成繁琐且易于编写的代码来节省时间。遵循约定优于配置方法，MapStruct使用合理的默认值，但在配置或实现特殊行为时会采取措施。

**与动态映射框架相比，MapStruct具有以下优势**：

- 通过使用普通方法调用而不是反射来快速执行
- 编译时类型安全：只能映射相互映射的对象和属性，不会将订单实体意外映射到客户DTO等。
- 在构建时清除错误报告，如果
    - 映射不完整（并非所有目标属性都已映射）
    - 映射不正确（找不到合适的映射方法或类型转换）

工作原理（使用java apt技术，该技术也用于lombok的实现）

1. 在代码编译时会触发MapStruct插件运行
2. 当MapStruct运起来之后会扫描它自己特定注解的类
3. 解析类中的方法按照自己的策略在项目编译目录（build）下生成实现类，如果生成过程中出现异常则会输出日志，并中断当前整个项目编译工作。

两个核心包的工作：

- *org.mapstruct：mapstruct*：包含所需的注释，例如`@Mapping`
- *org.mapstruct：mapstruct-processor*：包含生成映射器实现的注释处理器

## 三、MapStruct支持功能

- 一转一：支持浅拷贝，深拷贝，字段忽略
- 多转一：多个对象转化到一个对象中
- 集合转集合：list->list，map->map
- 类型转化: 数值转String，日期转String，自定义方法规则
- 枚举映射：转化指定枚举类型

## 四、MapStruct功能实践

com.itxiaolin.mapstruct

- dataType:类型转化: 数值转String，日期转String，自定义方法规则
- domain:各种JavaBean对象
- list2list：集合list->list
- map2map：集合map->map
- more2one：多个对象转化一个对象
- one2one：单个对象拷贝，浅拷贝，深拷贝，字段忽略

## 五、常用注解解释

1.1、@Mapper——表示该接口作为映射接口，编译时MapStruct处理器的入口

1）uese：外部引入的转换类；

2）componentModel：就是依赖注入，类似于在spring的servie层用@servie注入，那么在其他地方可以使用@Autowired取到值。该属性可取的值为

a）默认：这个就是经常使用的 xxxMapper.INSTANCE.xxx;

b）cdi：使用该属性，则在其他地方可以使用@Inject取到值；

c）spring：使用该属性，则在其他地方可以使用@Autowired取到值；

d）jsr330/Singleton：使用者两个属性，可以再其他地方使用@Inject取到值;

1.2、@Mappings——一组映射关系，值为一个数组，元素为@Mapping

1.3、@Mapping——一对映射关系

1）target：目标属性，赋值的过程是把“源属性”赋值给“目标属性”；

2）source：源属性，赋值的过程是把“源属性”赋值给“目标属性”；

3）dateFormat：用于源属性是Date，转化为String;

4）numberFormat：用户数值类型与String类型之间的转化;

5）constant：不管源属性，直接将“目标属性”置为常亮；

6）expression：使用表达式进行属性之间的转化;

7）ignore：忽略某个属性的赋值;

8）qualifiedByName：根据自定义的方法进行赋值;

9）defaultValue：默认值;

1.4、@MappingTarget——用在方法参数的前面。使用此注解，源对象同时也会作为目标对象，用于更新。

1.5、@InheritConfiguration——指定映射方法

1.6、@InheritInverseConfiguration——表示方法继承相应的反向方法的反向配置

1.7、@Named——定义类/方法的名称

