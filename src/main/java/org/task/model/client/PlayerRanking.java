package org.task.model.client;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerRanking {

   String id;
   Integer score;
   Integer ranking;

}
