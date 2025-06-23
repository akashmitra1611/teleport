package com.teleport.controller;

import com.teleport.dto.APIResponse;
import com.teleport.dto.HandledExceptions;
import com.teleport.model.TrackingResponse;
import com.teleport.service.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/core")
@Slf4j
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @Operation(summary = "Generate a unique tracking number")
    @GetMapping("/next-tracking-number")
    public ResponseEntity<APIResponse<TrackingResponse>> getNextTrackingNumber(
            @RequestParam("origin_country_id") String originCountryId,
            @RequestParam("destination_country_id") String destinationCountryId,
            @RequestParam("weight") String weight,
            @RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime createdAt,
            @RequestParam("customer_id") String customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug
    ) throws HandledExceptions, NoSuchAlgorithmException {

        log.info("Tracking Number Generation Request Recieved for => {} to {}",originCountryId,destinationCountryId);
        return ResponseEntity.ok(trackService.getNextTrackingNumber(
                originCountryId,destinationCountryId,weight,createdAt,customerId,customerName,customerSlug
        ));
    }

}
