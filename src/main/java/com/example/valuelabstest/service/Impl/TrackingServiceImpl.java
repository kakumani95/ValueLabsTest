package com.example.valuelabstest.service.Impl;

import com.example.valuelabstest.model.TrackingResponse;
import com.example.valuelabstest.service.TrackingService;
import com.example.valuelabstest.utils.TrackingNumberGenerator;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TrackingServiceImpl implements TrackingService {

    @Override
    public TrackingResponse getTrackingNumber() {
        TrackingResponse trackingResponse = new TrackingResponse();
        trackingResponse.setTracking_number(TrackingNumberGenerator.generateUniqueTrackingNumber());
        trackingResponse.setCreated_at(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(new Date()));
        return trackingResponse;
    }
}
