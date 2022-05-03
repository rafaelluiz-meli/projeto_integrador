package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Role;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String role_name) {
        return roleRepository.findByName(role_name);
    }

    @Override
    public Role save(String role_name) {
        return roleRepository.save(Role.builder().name(role_name).build());
    }
}
