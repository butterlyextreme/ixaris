package org.task.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task.data.entity.RankingsEntity;

public interface RankingsRepository extends JpaRepository<RankingsEntity, String> {
}
