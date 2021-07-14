package org.task.service;

import org.task.model.client.Match;
import org.task.model.client.PlayerRanking;

import java.util.List;
import java.util.Map;

public interface RankingsService {

    List<PlayerRanking> retrieveRankings();

    void createMatch(Match match);
}
