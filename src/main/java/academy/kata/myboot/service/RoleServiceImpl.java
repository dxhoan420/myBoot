package academy.kata.myboot.service;

import academy.kata.myboot.Repository.RoleRepository;
import academy.kata.myboot.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> getRolesById(Long[] ids) {
        return ids != null ? roleRepository.findAllById(Arrays.asList(ids)) : new ArrayList<>();
    }
}
