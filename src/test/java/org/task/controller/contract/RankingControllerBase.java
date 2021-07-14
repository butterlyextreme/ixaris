package org.task.controller.contract;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.task.service.RankingsService;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RankingControllerBase {

  @LocalServerPort
  private int port;

  @MockBean
  private RankingsService rankingsService;

  @BeforeEach
  public void clear() {
    reset(rankingsService);
    doThrow(new RuntimeException("internal_error")).when(rankingsService)
        .createMatch(argThat(match -> match.getScore().equals("internal_error")));
  }


  @BeforeAll
  public void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

}
