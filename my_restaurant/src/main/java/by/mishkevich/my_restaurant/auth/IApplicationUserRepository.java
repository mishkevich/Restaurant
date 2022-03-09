package by.mishkevich.my_restaurant.auth;

import java.util.Optional;

public interface IApplicationUserRepository {
    Optional<ApplicationUser> loadUserByUsername(String username);
}
