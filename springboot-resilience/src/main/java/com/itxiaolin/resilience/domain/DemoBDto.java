package com.itxiaolin.resilience.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DemoBDto {
    private String bId;
    private String desc;
}
