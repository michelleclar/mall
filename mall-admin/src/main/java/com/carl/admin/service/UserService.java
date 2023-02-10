package com.carl.admin.service;

import com.carl.parma.CartListParam;
import com.carl.parma.PageParam;
import com.carl.pojo.User;
import com.carl.utils.R;

public interface UserService {
    R userList(PageParam pageParam);

    R userRemove(CartListParam cartListParam);

    R userUpdate(User user);

    R userSave(User user);
}
