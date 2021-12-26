package qyq.example.graphql.javademo.fetchers;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里写得比较简单,没走数据库,直接将数据写死了,数据类型都没定义
 * 实际需要查数据库,根据实际情况,可以进行扩展
 *
 * @author qianyuqi
 * @since 2021-07-29 15:01
 */
@Component
public class GraphQLDataFetchers {

    /**
     * 获取所有的用户信息
     *
     * @return DataFetcher
     */
    public DataFetcher<List<Map<String, Object>>> getAllUserList() {
        return dataFetchingEnvironment -> userList;
    }


    /**
     * 根据用户编号,获取用户详情
     *
     * @return Map<String, Object>
     */
    public DataFetcher<Map<String, Object>> getUserByUserId() {
        return dataFetchingEnvironment -> {
            String userId = dataFetchingEnvironment.getArgument("userId");
            return userList
                    .stream()
                    .filter(book -> book.get("userId").equals(userId))
                    .findFirst()
                    .orElse(new HashMap<>());
        };
    }

    private static final List<Map<String, Object>> userList = Arrays.asList(
            ImmutableMap.of("userId", "1",
                    "name", "张三",
                    "age", 111,
                    "salary", 111.1),
            ImmutableMap.of("userId", "2",
                    "name", "李四",
                    "age", 222,
                    "salary", 222.2),
            ImmutableMap.of("userId", "3",
                    "name", "王五",
                    "age", 333,
                    "salary", 333.3)
    );


}
