package com.bootcamp.northwind.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsStatesResponse {
    private String id;
    private String stateName;
    private String stateAbbr;
    private String stateRegion;
}
