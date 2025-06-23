package com.teleport.service;

import com.teleport.dto.APIResponse;
import com.teleport.dto.CustomMessages;
import com.teleport.dto.HandledExceptions;
import com.teleport.model.TrackingResponse;
import com.teleport.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TrackService {

    private final CommonUtil commonUtil;

    public TrackService(CommonUtil commonUtil) {
        this.commonUtil = commonUtil;
    }

    /**
     * Method on Service Layer to enable generation of unique tracking id for given data
     * @param originCountryId
     * @param destinationCountryId
     * @param weight
     * @param createdAt
     * @param customerId
     * @param customerName
     * @param customerSlug
     * @return
     * @throws NoSuchAlgorithmException
     * @throws HandledExceptions
     */
    public APIResponse<TrackingResponse> getNextTrackingNumber(String originCountryId, String destinationCountryId, String weight, OffsetDateTime createdAt, String customerId, String customerName, String customerSlug) throws NoSuchAlgorithmException, HandledExceptions {

        String datePortion = createdAt.format(DateTimeFormatter.ofPattern("yyMMdd"));

        long millis = System.currentTimeMillis();
        int nanos = OffsetDateTime.now().getNano() % 1000;
        long microTimestamp = millis * 1000 + nanos;
        String timeStampPortion = Long.toString(microTimestamp, 36).toUpperCase();

        log.info("Executing Tracking ID Generation at => {}",timeStampPortion);

        String rawInput = originCountryId + destinationCountryId + weight + createdAt
                + customerId + customerName + customerSlug;

        String hashPortion = commonUtil.generateTrackingHash(
                rawInput, 4);

        String trackingNumber = (originCountryId + destinationCountryId + datePortion + timeStampPortion + hashPortion)
                .replaceAll("[^A-Z0-9]", "")
                .toUpperCase();

        if(!trackingNumber.isEmpty()){

            if (trackingNumber.length() > 16) {
                trackingNumber = trackingNumber.substring(0, 16);
            }

            return APIResponse.<TrackingResponse>builder()
                    .status(true)
                    .data(
                            TrackingResponse.builder()
                                    .trackingNumber(trackingNumber)
                                    .createdAt(OffsetDateTime.now())
                                    .build()
                    ).build();
        }

        throw new HandledExceptions(CustomMessages.INVALID_REQUEST.getValue());
    }
}
