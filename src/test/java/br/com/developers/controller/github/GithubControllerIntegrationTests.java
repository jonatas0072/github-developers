package br.com.developers.controller.github;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class GithubControllerIntegrationTests {

  private static final String QUALIFIER_USER = "muriloalvesdev";
  private static final String QUALIFIER_REPOSITORIE = "thehero-backend";

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void shouldSearchUsers() throws Exception {
    this.mockMvc
        .perform(get(GithubController.PATH + "search/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
            .param("qualifier", QUALIFIER_USER))
        .andExpect(status().isOk()).andExpect(jsonPath("$.total_count", is(1)))
        .andExpect(jsonPath("$.incomplete_results", is(false)))
        .andExpect(jsonPath("$.items[0].login", is("muriloalvesdev")))
        .andExpect(jsonPath("$.items[0].id", is(35849751)))
        .andExpect(jsonPath("$.items.[0].avatar_url",
            is("https://avatars0.githubusercontent.com/u/35849751?v=4")))
        .andExpect(jsonPath("$.items[0].html_url", is("https://github.com/muriloalvesdev")));
  }

  @Test
  void shouldSearchRepositories() throws Exception {
    this.mockMvc
        .perform(get(GithubController.PATH + "search/repositories")
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
            .param("qualifier", QUALIFIER_REPOSITORIE))
        .andExpect(status().isOk()).andExpect(jsonPath("$.total_count", is(1)))
        .andExpect(jsonPath("$.incomplete_results", is(false)))
        .andExpect(jsonPath("$.items[0].private", is(false)))
        .andExpect(jsonPath("$.items[0].full_name", is("muriloalvesdev/thehero-backend")))
        .andExpect(jsonPath("$.items[0].description", is(
            "Este projeto foi desenvolvido com o intuito de ajudar ONGs que cuidam de animais.")))
        .andExpect(jsonPath("$.items[0].html_url",
            is("https://github.com/muriloalvesdev/thehero-backend")))
        .andExpect(jsonPath("$.items[0].created_at", is("2020-04-21T18:52:53Z")))
        .andExpect(jsonPath("$.items[0].clone_url",
            is("https://github.com/muriloalvesdev/thehero-backend.git")))
        .andExpect(jsonPath("$.items[0].homepage", is("https://thehero.netlify.app/")))
        .andExpect(jsonPath("$.items[0].language", is("Java")))
        .andExpect(jsonPath("$.items[0].license.key", is("apache-2.0")))
        .andExpect(jsonPath("$.items[0].license.name", is("Apache License 2.0")))
        .andExpect(jsonPath("$.items[0].license.spdx_id", is("Apache-2.0")))
        .andExpect(
            jsonPath("$.items[0].license.url", is("https://api.github.com/licenses/apache-2.0")))
        .andExpect(jsonPath("$.items[0].default_branch", is("master")))
        .andExpect(jsonPath("$.items[0].owner.login", is("muriloalvesdev")))
        .andExpect(jsonPath("$.items[0].owner.id", is(35849751)))
        .andExpect(jsonPath("$.items.[0].owner.avatar_url",
            is("https://avatars0.githubusercontent.com/u/35849751?v=4")))
        .andExpect(jsonPath("$.items[0].owner.html_url", is("https://github.com/muriloalvesdev")));
  }
}
