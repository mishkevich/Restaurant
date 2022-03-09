//package by.mishkevich.my_restaurant.auth;
//
//import by.mishkevich.my_restaurant.entity.enums.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class ApplicationUserRepository implements IApplicationUserRepository {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Optional<ApplicationUser> loadUserByUsername(String username) {
//        return getUsers().stream()
//                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
//                .findFirst();
//    }
//
//    private List<ApplicationUser> getUsers() {
//        List<ApplicationUser> users = List.of(
//                new ApplicationUser(
//                        Role.GUEST.getGrantedAuthority(),
//                        "guest",
//                        passwordEncoder.encode("guest"),
//                        true, true,
//                        true, true
//                ),
//                new ApplicationUser(
//                        Role.USER.getGrantedAuthority(),
//                        "user",
//                        passwordEncoder.encode("user"),
//                        true, true,
//                        true, true
//                ),
//                new ApplicationUser(
//                        Role.ADMIN.getGrantedAuthority(),
//                        "admin",
//                        passwordEncoder.encode("admin"),
//                        true, true,
//                        true, true
//                ),
//
//                new ApplicationUser(
//                        Role.DIRECTOR.getGrantedAuthority(),
//                        "director",
//                        passwordEncoder.encode("director"),
//                        true, true,
//                        true, true
//                )
//        );
//        return users;
//    }
//}
