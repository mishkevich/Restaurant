package by.mishkevich.my_restaurant.entity.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static by.mishkevich.my_restaurant.entity.enums.UserPermission.*;


public enum Role {
    GUEST(Sets.newHashSet(INFO_READ)),
    USER(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ)),
    ADMIN(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ, ADMIN_WRITE)),
    DIRECTOR(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ, ADMIN_WRITE, ADMIN_CONTROL));


    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return this.permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> permissions = this
                .getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
