package narrato.users.usecases.followuser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import narrato.BaseIT;
import narrato.users.usecases.UserModuleTest;
import narrato.users.usecases.shared.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UserModuleTest
class FollowUserControllerTests extends BaseIT {
    @Autowired
    JwtHelper jwtHelper;

    @Test
    void shouldFollowUserSuccessfully() throws Exception {
        String token = jwtHelper.generateToken("james@gmail.com");
        mockMvc.perform(post("/api/profiles/{username}/follow", "siva").header("Authorization", "Token " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profile.username").value("siva"))
                .andExpect(jsonPath("$.profile.bio").value("I am a Software Architect"))
                .andExpect(jsonPath("$.profile.image").value("https://api.realworld.io/images/demo-avatar.png"))
                .andExpect(jsonPath("$.profile.following").value(true));
    }

    @Test
    void shouldGetUnauthorizedWithoutToken() throws Exception {
        mockMvc.perform(post("/api/profiles/{username}/follow", "siva")).andExpect(status().isUnauthorized());
    }
}
