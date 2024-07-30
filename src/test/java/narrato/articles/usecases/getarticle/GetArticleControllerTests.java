package narrato.articles.usecases.getarticle;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import narrato.BaseIT;
import narrato.articles.ArticleModuleTest;
import org.junit.jupiter.api.Test;

@ArticleModuleTest
class GetArticleControllerTests extends BaseIT {

    @Test
    void shouldGetArticleSuccessfullyAsGuest() throws Exception {
        mockMvc.perform(get("/api/articles/{slug}", "getting-started-with-kubernetes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article.slug").value("getting-started-with-kubernetes"))
                .andExpect(jsonPath("$.article.title").value("Getting Started with Kubernetes"))
                .andExpect(
                        jsonPath("$.article.description")
                                .value(
                                        "In this article we will learn Creating a docker image from a SpringBoot application, Local kubernetes setup using Minikube, Run the SpringBoot app in a Pod, Scaling the application using Deployment, Exposing the Deployment as a Service"))
                .andExpect(
                        jsonPath("$.article.body")
                                .value(
                                        "In this article we will learn Creating a docker image from a SpringBoot application, Local kubernetes setup using Minikube, Run the SpringBoot app in a Pod, Scaling the application using Deployment, Exposing the Deployment as a Service"))
                .andExpect(jsonPath("$.article.tagList", hasItems("kubernetes")))
                .andExpect(jsonPath("$.article.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.article.updatedAt", nullValue()))
                .andExpect(jsonPath("$.article.favorited").value(false))
                .andExpect(jsonPath("$.article.favoritesCount").value(2))
                .andExpect(jsonPath("$.article.author.username").value("admin"))
                .andExpect(jsonPath("$.article.author.bio").value("I am a system administrator"))
                .andExpect(jsonPath("$.article.author.image").value("https://api.realworld.io/images/demo-avatar.png"))
                .andExpect(jsonPath("$.article.author.following").value(false));
    }
}
