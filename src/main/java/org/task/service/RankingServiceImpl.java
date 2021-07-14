package org.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.task.data.entity.MatchesEntity;
import org.task.data.entity.RankingsEntity;
import org.task.data.respository.MatchesRepository;
import org.task.data.respository.RankingsRepository;
import org.task.model.Player;
import org.task.model.Ranking;
import org.task.model.client.Match;
import org.task.model.client.PlayerRanking;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingsService {

    public static final int INCREMENT_WIN = 3;
    public static final int INCREMENT_LOSE = -2;
    private final MatchesRepository matchesRepository;
    private final RankingsRepository rankingsRepository;

    public List<PlayerRanking> retrieveRankings() {
        log.debug("About to retrieve all rankings");

        //Retrieve the match scores
        Map<String, Integer> players = matchesRepository.findAll()
                .stream()
                .map(this::toPlayers)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Player::getId,
                        Collectors.summingInt(Player::getIncrement)));

        //Retrieve the original rankings
        Map<String, Integer> rankings = rankingsRepository.findAll()
                .stream()
                .map(this::toRanking)
                .collect(
                        Collectors.toMap(Ranking::getPlayerId, Ranking::getScore));

        //Merge the results
        Map<String, Integer> result = Stream.of(players, rankings)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)))
                .entrySet().stream()
                .peek(e -> e.setValue(Math.max(e.getValue(), 0)))
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        //Format and return
        return toPlayerRankings(result);
    }


    public void createMatch(Match match) {
        log.debug("About to add match [{}]", match);
        MatchesEntity entity = matchesRepository.save(toMatchEntity(match));
        log.info("Successfully added match, id: [{}]", entity.getId());
    }

    private MatchesEntity toMatchEntity(Match match) {
        return MatchesEntity.builder().playerLose(match.getPlayerLose())
                .playerWin(match.getPlayerWin())
                .matchDate(match.getMatchDate().toInstant())
                .score(match.getScore())
                .build();
    }

    private List<PlayerRanking> toPlayerRankings(final Map<String, Integer> result) {
        AtomicInteger scoreHolder = new AtomicInteger(-1);
        AtomicInteger rank = new AtomicInteger(0);

        List<PlayerRanking> playerRankings = new ArrayList<>();

        result.forEach((k, v) -> {
            if (v != scoreHolder.get()) {
                rank.getAndIncrement();
            }
            playerRankings.add(toRanking(k, v, rank));
            scoreHolder.set(v);
        });
        return playerRankings;
    }

    private PlayerRanking toRanking(final String player, final Integer score, final AtomicInteger rank) {
        return PlayerRanking.builder().id(player)
                .score(score)
                .ranking(rank.get())
                .build();
    }

    private Ranking toRanking(final RankingsEntity ranking) {
        return Ranking.builder()
                .playerId(ranking.getId())
                .score(ranking.getPoints()).build();
    }

    private List<Player> toPlayers(final MatchesEntity match) {
        List<Player> players = new ArrayList<>();
        players.add(Player.builder().id(match.getPlayerWin()).increment(INCREMENT_WIN).build());
        players.add(Player.builder().id(match.getPlayerLose()).increment(INCREMENT_LOSE).build());
        return players;
    }
}
