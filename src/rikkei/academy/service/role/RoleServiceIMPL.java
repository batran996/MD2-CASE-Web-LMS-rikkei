package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService{
    ////fig cứng list tài khoản role
    public static List<Role> roleList = new ArrayList<>();
    static{
        roleList.add( new Role(1,RoleName.USER));
        roleList.add(new Role(2,RoleName.ADMIN));
    }
    @Override
    ///hàm lấy list role
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleList.add(role);
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        for (Role role:roleList) {
            if (role.getRoleName() == roleName){
                return role;
            }
        }
        return null;
    }

}






