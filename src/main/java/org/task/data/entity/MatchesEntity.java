package org.task.data.entity;


import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@Table(name = "matches")
@Entity(name = "matches")
public class MatchesEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "match_date")
    private Instant matchDate;

    @Column(name = "player_win")
    private String  playerWin;

    @Column(name = "player_lose")
    private String  playerLose;

}
