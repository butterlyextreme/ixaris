package org.task.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.task.data.entity.MatchesEntity;
import org.task.data.entity.RankingsEntity;
import org.task.data.respository.MatchesRepository;
import org.task.data.respository.RankingsRepository;
import org.task.model.client.PlayerRanking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RankingsServiceImplTest {

    @Mock
    private MatchesRepository matchesRepository;

    @Mock
    private RankingsRepository rankingsRepository;

    @InjectMocks
    private RankingServiceImpl rankingService;

    @Test
    void testReorderRankingsWithMatches() {

        when(matchesRepository.findAll()).thenReturn(generateMatches());
        when(rankingsRepository.findAll()).thenReturn(generateRankings());
        List<PlayerRanking> playerRankings = rankingService.retrieveRankings();

        String result = "[PlayerRanking(id=001, score=109, ranking=1), " +
                "PlayerRanking(id=003, score=8, ranking=2), " +
                "PlayerRanking(id=004, score=8, ranking=2), " +
                "PlayerRanking(id=002, score=0, ranking=3)]";

        assertEquals(result, playerRankings.toString());

    }

    @Test
    void testReorderRankingsWithNoMatches() {

        when(rankingsRepository.findAll()).thenReturn(generateRankings());
        List<PlayerRanking> playerRankings = rankingService.retrieveRankings();

        String result = "[PlayerRanking(id=001, score=100, ranking=1), " +
                "PlayerRanking(id=002, score=5, ranking=2), " +
                "PlayerRanking(id=003, score=5, ranking=2), " +
                "PlayerRanking(id=004, score=5, ranking=2)]";

        assertEquals(result, playerRankings.toString());

    }

    private List<MatchesEntity> generateMatches() {
        List<MatchesEntity> matchesEntities = new ArrayList<>();
        matchesEntities.add(MatchesEntity.builder().id(UUID.randomUUID()).playerWin("001").playerLose("002").build());
        matchesEntities.add(MatchesEntity.builder().id(UUID.randomUUID()).playerWin("001").playerLose("002").build());
        matchesEntities.add(MatchesEntity.builder().id(UUID.randomUUID()).playerWin("001").playerLose("002").build());
        matchesEntities.add(MatchesEntity.builder().id(UUID.randomUUID()).playerWin("003").playerLose("002").build());
        matchesEntities.add(MatchesEntity.builder().id(UUID.randomUUID()).playerWin("004").playerLose("002").build());
        return matchesEntities;
    }

    private List<RankingsEntity> generateRankings() {
        List<RankingsEntity> rankingsEntities = new ArrayList<>();
        rankingsEntities.add(RankingsEntity.builder().id("001").points(100).build());
        rankingsEntities.add(RankingsEntity.builder().id("003").points(5).build());
        rankingsEntities.add(RankingsEntity.builder().id("004").points(5).build());
        rankingsEntities.add(RankingsEntity.builder().id("002").points(5).build());
        return rankingsEntities;
    }

}
