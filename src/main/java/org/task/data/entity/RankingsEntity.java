package org.task.data.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Builder(toBuilder = true)
@Table(name = "rankings")
@Entity(name = "rankings")
public class RankingsEntity {

    @Id
    private String id;

    private int points;
    private int ranking;

    @Column(name = "publication")
    private Instant publication;
}
