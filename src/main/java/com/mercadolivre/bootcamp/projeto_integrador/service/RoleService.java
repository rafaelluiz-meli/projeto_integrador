package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Role;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface RoleService {
    Role findByName(String role_name);
    Role save(String role_name);
}
