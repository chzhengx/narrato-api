package narrato.users.usecases.getcurrentuser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import narrato.BaseIT;
import narrato.users.usecases.UserModuleTest;
import narrato.users.usecases.shared.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UserModuleTest
class GetCurrentUserControllerTests extends BaseIT {
    @Autowired
    JwtHelper jwtHelper;

    @Test
    void shouldReturnLoginUserInformationGivenValidToken() throws Exception {
        String token = jwtHelper.generateToken("siva@gmail.com");
        mockMvc.perform(get("/api/user").header("Authorization", "Token " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value("siva@gmail.com"))
                .andExpect(jsonPath("$.user.token").isNotEmpty())
                .andExpect(jsonPath("$.user.username").value("siva"))
                .andExpect(jsonPath("$.user.bio").value("I am a Software Architect"))
                .andExpect(jsonPath("$.user.image").value("https://api.realworld.io/images/demo-avatar.png"));
    }

    @Test
    void shouldReturnUnauthorizedWithoutToken() throws Exception {
        mockMvc.perform(get("/api/user")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnUnauthorizedWithInvalidToken() throws Exception {
        mockMvc.perform(get("/api/user").header("Authorization", "Token invalid_token"))
                .andExpect(status().isUnauthorized());
    }
}
