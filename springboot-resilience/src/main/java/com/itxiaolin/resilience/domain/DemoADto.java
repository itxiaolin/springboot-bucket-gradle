package com.itxiaolin.resilience.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DemoADto {
    private String aId;
    private String desc;
    private DemoBDto demoBDto;
}
