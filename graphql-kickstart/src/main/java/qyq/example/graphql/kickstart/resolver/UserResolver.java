package qyq.example.graphql.kickstart.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import qyq.example.graphql.kickstart.bean.entity.UserInfo;
import qyq.example.graphql.kickstart.service.UserService;

import javax.annotation.Resource;

/**
 * @author qianyuqi
 * @since 2021-12-26 17:01
 */
@Component
public class UserResolver implements GraphQLQueryResolver {

    @Resource
    private UserService userService;

    /**
     * 随机查询一个用户信息
     */
    public UserInfo findUser() {
        return userService.findUser();
    }

    /**
     * 根据用户编号,获取用户详情
     */
    public UserInfo findUserDetail(String userId) {
        return userService.findUserDetail(userId);
    }
}
