package org.task.data.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.task.data.entity.MatchesEntity;

import java.util.UUID;


public interface MatchesRepository extends JpaRepository<MatchesEntity, UUID> {

}
