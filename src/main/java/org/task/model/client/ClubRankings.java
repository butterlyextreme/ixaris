package org.task.model.client;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClubRankings {
    @JsonProperty("player_rankings")
    List<PlayerRanking> players;
}
