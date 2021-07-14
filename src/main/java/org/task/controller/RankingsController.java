package org.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.task.model.client.Match;
import org.task.model.client.PlayerRanking;
import org.task.service.RankingsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Validated
@RestController
@RequiredArgsConstructor
@Slf4j
public class RankingsController {

    private final RankingsService rankingsService;

    @GetMapping(value = "/rankings")
    public ResponseEntity<List<PlayerRanking>> getRankings() {
        return new ResponseEntity<>(rankingsService.retrieveRankings(), HttpStatus.OK);
    }

    @PostMapping(value = "/match",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMatch(@Valid @RequestBody final Match match) {
        rankingsService.createMatch(match);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
