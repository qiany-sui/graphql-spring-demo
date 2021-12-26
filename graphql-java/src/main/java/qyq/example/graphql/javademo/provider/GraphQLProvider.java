package qyq.example.graphql.javademo.provider;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import qyq.example.graphql.javademo.fetchers.GraphQLDataFetchers;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * 注册GraphQL
 *
 * @author qianyuqi
 * @since 2021-12-25 15:02
 */
@Slf4j
@Component
public class GraphQLProvider {
    private GraphQL graphQL;

    // GraphQL方法的具体业务逻辑
    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;


    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    /**
     * 服务启动时进行GraphQL文件的加载
     * 1、读取本地GraphQL文件的路径
     * 2、根据路径读取出GraphQL文件的内容
     * 3、根据GraphQL文件内容,通过SchemaParser,将其转换为GraphQL能够识别的Schema
     * 4、拿着Schema初始化GraphQL
     */
    @PostConstruct
    public void init() {
        // 读取文件的名称
        // 此为demo,所以扫描的时候,包名是写死的
        List<String> graphqlFileNames = getGraphqlFileNames();
        if (CollectionUtils.isEmpty(graphqlFileNames)) {
            throw new RuntimeException("无法找到graphql文件");
        }
        // 读取出所有GraphQL文件的内容,并放入到一个List中
        List<String> sdls = getAllSdl(graphqlFileNames);
        // 初始化Graphql
        this.graphQL = GraphQL.newGraphQL(buildSchema(sdls)).build();
    }

    /**
     * 读取resources下所有GraphQL文件的路径与名称并返回
     *
     * @return GraphQL文件路径 + 名称
     */
    private List<String> getGraphqlFileNames() {
        List<String> fileNames;
        File file;
        try {
            file = ResourceUtils.getFile("classpath:graphql");
        } catch (FileNotFoundException e) {
            log.error("getGraphqlFileNames FileNotFoundException", e);
            return Collections.emptyList();
        }
        File[] files = file.listFiles();
        if (file.exists() && files != null && files.length > 0) {
            fileNames = Arrays.stream(files).map(n -> "graphql/" + n.getName()).collect(Collectors.toList());
        } else {
            fileNames = new ArrayList<>();
        }
        return fileNames;
    }

    /**
     * 读取出所有GraphQL文件的内容,并放入到一个List中
     *
     * @param graphqlFileNames graphql文件的路径名 + 名称
     * @return 返回包含所有graphql文件内容的集合
     */
    private List<String> getAllSdl(List<String> graphqlFileNames) {
        List<String> sdlList = new ArrayList<>();
        for (String graphqlName : graphqlFileNames) {
            try {
                sdlList.add(IOUtils.toString(new ClassPathResource(graphqlName).getInputStream(), StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sdlList;
    }

    /**
     * 生成GraphQL的schema
     * 1、生成GraphqlSchema
     * 2、加载每个GraphQL查询、变更中所包含的方法的具体业务逻辑
     *
     * @param sdlList graphql文件内容
     * @return GraphQL-schema
     */
    private GraphQLSchema buildSchema(List<String> sdlList) {
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        // each registry is merged into the main registry
        SchemaParser schemaParser = new SchemaParser();
        for (String schemaInput : sdlList) {
            typeRegistry.merge(schemaParser.parse(schemaInput));
        }
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * 加载GraphQL每个方法的业务逻辑
     *
     * @return
     */
    private RuntimeWiring buildWiring() {
        // 由于我这边只写了Query,所以只需要加载这一个就可以
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("findUserList", graphQLDataFetchers.getAllUserList())
                        .dataFetcher("findUserDetail", graphQLDataFetchers.getUserByUserId())
                )
//                .type(newTypeWiring("Book")
//                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
//                .type("Human", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsDataFetcher.getFriendsDataFetcher()))
//                // you can use builder syntax if you don't like the lambda syntax
//                .type("Droid", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsDataFetcher.getFriendsDataFetcher()))
//                // or full builder syntax if that takes your fancy
//                .type(newTypeWiring("Character")
//                        .typeResolver(StarWarsDataFetcher.getCharacterTypeResolver()).build())
//                .type(newTypeWiring("Episode")
//                        .enumValues(StarWarsDataFetcher.getEpisodeProvider()))
                .build();
    }
}


