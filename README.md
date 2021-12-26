# 服务整体介绍

该服务为GraphQL服务端代码实现的Demo,分为两个模块graphql-java、graphql-kickstart,这两个模块分别引入不同的jar包,来演示graphql服务端代码的实现

## 一、模块说明

1. graphql-java模块: 使用官方原生的文件进行构建
2. graphql-kickstart模块: 使用kickstart提供的工具包进行构建

### 建议

- 若是时间充裕,想要完全自定义graphql,进行深入改造: 推荐使用官方原生jar包
- 若只是简单使用,并不需要了解graphql内部的工作流程: 推荐使用kickstart提供的包,使用起来更加方便

### 注意

1. 由于官方原生的jar,定义、调用GraphQL比较费劲,所有在graphql-java模块下,仅仅简单得写了两个例子,具体的还是需要看graphql-kickstart模块
2. 由于kickstart的graphiql与playground两者存在配置冲突的情况,而graphql-kickstart模块我这边不打算引入graphql基本包,所以此处就所有的GraphQL客户端结构体,只能在**playground**中进行验证


## 二、模块内文件结构的简单说明

1. 所有服务端grpahql定义文件,都放在resources/graphql中,文件以.graphql为后缀进行的命名
2. 所有用于测试的客户端graphql结构体,都放在resources/graphql-clinet-test中,文件以.txt为后缀进行命令