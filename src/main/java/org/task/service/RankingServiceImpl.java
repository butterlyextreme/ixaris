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
import org.task.model.client.PlayerRanking;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingsService {

    private final MatchesRepository matchesRepository;
    private final RankingsRepository rankingsRepository;

    public List<PlayerRanking> retrieveRankings() {

        Map<String, Integer> players = matchesRepository.findAll()
                .stream()
                .map(this::toPlayers)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Player::getId,
                        Collectors.summingInt(Player::getIncrement)));

        Map<String, Integer> rankings = rankingsRepository.findAll()
                .stream()
                .map(this::toRanking)
                .collect(
                        Collectors.toMap(Ranking::getPlayerId, Ranking::getScore));


        Map<String, Integer> result = Stream.of(players, rankings)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)))
                .entrySet().stream()
                .peek(e -> e.setValue(Math.max(e.getValue(), 0)))
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return toPlayerRankings(result);
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
        players.add(Player.builder().id(match.getPlayerWin()).increment(3).build());
        players.add(Player.builder().id(match.getPlayerLose()).increment(-2).build());
        return players;
    }
}
