package org.task.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Ranking {

    String playerId;
    int score;
}
