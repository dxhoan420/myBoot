package academy.kata.myboot.service;

import academy.kata.myboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRole(Long id);
    List<Role> getRolesById(Long [] ids);
}
