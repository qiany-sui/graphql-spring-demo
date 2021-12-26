package qyq.example.graphql.kickstart.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import qyq.example.graphql.kickstart.bean.entity.UserInfo;
import qyq.example.graphql.kickstart.mapper.UserInfoMapper;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * @author qianyuqi
 * @since 2021-12-26 17:09
 */
@Service
public class UserService {
    @Resource
    private UserInfoMapper userInfoMapper;


    /**
     * 随机查询一个用户信息
     * 1、查询数据库中所有信息,包含删除的
     * 为空,直接返回
     * 2、(不为空) 拿着返回的用户列表,获取个数,生成随机数
     * 3、根据获取到的随机数,作为下标,返回数据
     *
     * @return UserInfo
     */
    @SneakyThrows
    public UserInfo findUser() {
        List<UserInfo> userInfos = userInfoMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isEmpty(userInfos)) {
            return new UserInfo();
        }
        int index = SecureRandom.getInstanceStrong().nextInt(userInfos.size());
        return userInfos.get(index);
    }

    /**
     * 根据用户编号,获取用户详情
     * @return UserInfo
     */
    public UserInfo findUserDetail(String userId) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserId, userId));
    }
}
