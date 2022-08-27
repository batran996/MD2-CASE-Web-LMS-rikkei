package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;

import java.util.List;

public interface IRoleService {
    ///hàm tìm kiếm theo Role name và lấy về list role name
    List<Role> findAll();
    void save(Role role);

    //    hàm tìm kiếm role qua tên
    Role findByRoleName(RoleName roleName);
}
