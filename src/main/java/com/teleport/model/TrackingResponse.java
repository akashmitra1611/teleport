package com.teleport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponse {

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

}
