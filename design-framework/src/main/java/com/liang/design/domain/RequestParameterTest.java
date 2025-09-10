package com.liang.design.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestParameterTest {
    private Integer requestParameterId;
    private String requestParameterName;
    private String requestParameterValue;
}
