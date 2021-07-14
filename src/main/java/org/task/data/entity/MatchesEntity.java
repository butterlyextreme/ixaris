package org.task.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "score")
    private String score;

    @Column(name = "match_date")
    private Instant matchDate;

    @Column(name = "player_win")
    private String  playerWin;

    @Column(name = "player_lose")
    private String  playerLose;

}
