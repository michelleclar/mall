package com.carl.clients;

import com.carl.parma.CartListParam;
import com.carl.parma.PageParam;
import com.carl.pojo.User;
import com.carl.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service")
public interface UserClient {

    /**
     * 后台管理,展示用户信息接口
     * @param pageParam
     * @return
     */
    @PostMapping("/user/admin/list")
    R adminListPage(@RequestBody PageParam pageParam);

    /**
     * 后台管理,删除用户接口
     * @param cartListParam
     * @return
     */
    @PostMapping("/user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("/user/admin/update")
    R adminUpdate(@RequestBody User user);

    @PostMapping("/user/admin/save")
    R adminSave(@RequestBody User user);
}
