package org.task.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Player {

    String id;
    int increment;
}
