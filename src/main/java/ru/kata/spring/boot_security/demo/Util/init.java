package ru.kata.spring.boot_security.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class init {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void startDB() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        List<Role> roleSet = new ArrayList<>();
        roleSet.add(roleAdmin);
        roleSet.add(roleUser);
        User admin = new User("admin2", "admin", "admin@gmail.com", 22,
                "$2a$12$29mL7hCrkQ01SgK.yEQ5ceeNLp0gUwamoNCX90Dm6pNLt/zQXuxbq", roleSet);
        userService.saveUser(admin);
    }
}
