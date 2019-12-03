package com.leyou.server;

import com.leyou.common.Exceptions.LyException;
import com.leyou.common.eEnum.ExceptionEnum;
import com.leyou.mapper.UserMapper;
import com.leyou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public Boolean checkData(String data, Integer type) {
        User record = new User();
        //判断数据类型
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                throw new LyException(ExceptionEnum.INVAID_USER_DATA_TYPE);
        }
        int count = userMapper.selectCount(record);
        return count == 0;
    }
}
