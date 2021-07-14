package org.task.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Match {

    String score;
    @JsonProperty("player_win")
    String playerWin;
    @JsonProperty("player_lose")
    String playerLose;
    @JsonProperty("match_date")
    Date matchDate;

}
