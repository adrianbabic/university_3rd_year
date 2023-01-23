package www;

import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import www.exception.NoSuchStudentException;
import www.models.Admin;
import www.models.Instruktor;
import www.models.Student;
import www.models.User;
import www.repository.AdminRepository;
import www.repository.StudentRepository;
import www.repository.UserRepository;
import www.security.config.JWTToken.JwtToken;
import www.service.InstruktorService;
import www.service.StudentService;
import www.service.UserDetailsServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;
import java.util.Optional;



@SpringBootTest
@AutoConfigureMockMvc
class JUnitTests {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtToken token;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void registerTest() throws Exception {
        String string = "{\"email\":\"john@gmail.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"password\":\"lozinka\",\"type\":\"student\"}";
        this.mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void loginTest() throws Exception {
        User user = new Student();
        user.setEmail("john@gmail.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setHashedPassword(encoder.encode("lozinka"));
        userRepository.save(user);

        String string = "{\"email\":\"john@gmail.com\",\"password\":\"lozinka\"}";
        this.mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    @Test
    void findByEmailTest() throws NoSuchStudentException {
        Optional<Student> student = Optional.of(studentService.getStudentWithEmail("mia@gmail.com"));
        assertThat(student.isPresent());
    }

    @Test
    void findByBadEmailTest() {
        assertThrows(NoSuchStudentException.class, () -> {
            Optional<Student> student = Optional.of(studentService.getStudentWithEmail("nepostojimail@gmai.com"));
        });
    }

    @Test
    void saveAndFindUserTest() throws NoSuchStudentException {
        Student student = new Student("gmail@gmail.com", "hashedPassword", "firstName", "lastName");
        studentRepository.save(student);
        Optional<Student> s1 = Optional.ofNullable(studentService.getStudentWithEmail("gmail@gmail.com"));
        assertThat(s1.isPresent());
    }

    @Test
    void gettingAllInstruktorsAsAdminTest() throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));
        String jwt = token.generateTokenValue(authentication);
        this.mockMvc.perform(get("/api/a/instruktori").header("Authorization", "Bearer " + jwt)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void gettingAllInstruktorsAsStudentTest() throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("mia@gmail.com", "mia123"));
        String jwt = token.generateTokenValue(authentication);
        this.mockMvc.perform(get("/api/s/instruktori").header("Authorization", "Bearer " + jwt)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void gettingAllInstruktorsAsInstruktorTest() throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("miljenko@gmail.com", "miljenko123"));
        String jwt = token.generateTokenValue(authentication);
        this.mockMvc.perform(get("/api/i/instruktori").header("Authorization", "Bearer " + jwt)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void instruktorWithIdExistsTest() throws Exception {
        User user = new Instruktor();
        user.setEmail("bobo@gmail.com");
        user.setFirstName("Bob");
        user.setLastName("Bobic");
        user.setHashedPassword(encoder.encode("lozinka"));
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("mia@gmail.com", "mia123"));
        String jwt = token.generateTokenValue(authentication);
        this.mockMvc.perform(get("/api/s/instruktor/id=2").header("Authorization", "Bearer " + jwt)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void forbiddenApiTest() throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("mia@gmail.com", "mia123"));
        String jwt = token.generateTokenValue(authentication);

        this.mockMvc.perform(get("/api/a/studenti").header("Authorization", "Bearer " + jwt)).andExpect(status().isForbidden());
    }

    @Test
    void deleteInstruktor() throws Exception {
        User user = new Instruktor();
        user.setEmail("bobo@gmail.com");
        user.setFirstName("Bob");
        user.setLastName("Bobic");
        user.setHashedPassword(encoder.encode("lozinka"));
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));
        String jwt = token.generateTokenValue(authentication);

        this.mockMvc.perform(delete("/api/a/instruktori/id=2").header("Authorization", "Bearer " + jwt)).andExpect(status().isAccepted());
    }

}