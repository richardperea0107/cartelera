/*    package com.Cartelera.cartelera.Login;

    import org.springframework.boot.CommandLineRunner;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.crypto.password.PasswordEncoder;

    import com.Cartelera.cartelera.Model.Usuarios;
    import com.Cartelera.cartelera.Repository.UsuarioRepository;

    @Configuration
    public class DataInitializer {
        @Bean
        CommandLineRunner init(
                UsuarioRepository usuarioRepo,
                PasswordEncoder encoder) {

            return args -> {

                // Validar si el usuario ya existe
                if (usuarioRepo.findByUser("admin").isEmpty()) {

                    Usuarios user = new Usuarios();
                    user.setUser("admin");
                    user.setNombre("Admin");
                    user.setApellidos("Prueba");
                    user.setContrasena(encoder.encode("B@rion0101")); // 🔐 encriptada
                    user.setRol("ROLE_ADMIN");
                    user.setTelefono(0);

                    usuarioRepo.save(user);

                    System.out.println("Usuario admin creado");
                } else {
                    System.out.println("El usuario admin ya existe");
                }

                if (usuarioRepo.findByUser("user").isEmpty()) {
                    Usuarios user = new Usuarios();
                    user.setUser("user");
                    user.setNombre("Usuario");
                    user.setApellidos("Normal");
                    user.setContrasena(encoder.encode("User123!"));
                    user.setRol("ROLE_USER");
                    user.setTelefono(0);

                    usuarioRepo.save(user);
                    System.out.println("Usuario user creado");
                }
            };
        }
    }
 */