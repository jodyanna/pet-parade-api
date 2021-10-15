package com.petparade.api.controller;

import com.petparade.api.AbstractTest;
import com.petparade.api.dto.RatingDto;
import com.petparade.api.dto.UserDto;
import com.petparade.api.dto.UserStatsDto;
import com.petparade.api.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;

public class UserControllerTest extends AbstractTest {
  @Override
  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllUsers() throws Exception {
    String uri = "/users";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    UserDto[] users = super.mapFromJson(content, UserDto[].class);
    Assertions.assertTrue(users.length > 0);
  }

  @Test
  public void save() throws Exception {
    String uri = "/users/signup";
    UserDto testUser = setupTestUserDto();

    String inputJson = super.mapToJson(testUser);
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    UserDto response = super.mapFromJson(content, UserDto.class);
    Assertions.assertEquals(response, testUser);
  }

  // Utility methods
  private UserDto setupTestUserDto() {
    UserDto testUser = new UserDto();

    testUser.setUsername("test");
    testUser.setEmail("test@test.com");
    testUser.setPassword("test");
    testUser.setDateCreated(new Date(1));
    testUser.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
    testUser.setPets(Arrays.asList(1L, 2L, 3L));
    testUser.setLikedPets(Arrays.asList(4L, 5L));
    // Set ratings
    RatingDto rating1 = new RatingDto(3, 1L, 4L, new Date());
    RatingDto rating2 = new RatingDto(3, 1L, 5L, new Date());
    testUser.setRatings(Arrays.asList(rating1, rating2));
    // Set stats
    testUser.setStats(new UserStatsDto(3, 0, 2, 2, 0));

    return testUser;
  }
}
