package org.task.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "rankings")
@Entity(name = "rankings")
public class RankingsEntity {

    @Id
    private String id;

    private int points;
    private int ranking;

    @Column(name = "publication_date")
    private Instant publicationDate;
}
