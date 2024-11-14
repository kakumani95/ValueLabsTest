package com.example.valuelabstest.controller;

import com.example.valuelabstest.model.TrackingResponse;
import com.example.valuelabstest.service.Impl.TrackingServiceImpl;
import com.example.valuelabstest.validators.TrackingRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TrackingController {

    @Autowired
    TrackingServiceImpl trackingService;

    @GetMapping("next-tracking-number")
    public ResponseEntity<?> getTrackingNumber(@RequestParam String origin_country_id,
                                               @RequestParam String destination_country_id,
                                               @RequestParam String weight,
                                               @RequestParam String created_at,
                                               @RequestParam String customer_id,
                                               @RequestParam String customer_name,
                                               @RequestParam String customer_slug) {
        try {
            List<String> errors = TrackingRequestValidator.validateParameters(
                    origin_country_id,
                    destination_country_id,
                    weight,
                    created_at,
                    customer_id,
                    customer_name,
                    customer_slug
            );

            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            // If validation is successful, get tracking number
            TrackingResponse trackingResponse = trackingService.getTrackingNumber();
            return ResponseEntity.ok(trackingResponse);


//            User user = userService.getUserDetails(userId);
//            if (user != null) {
//                return new ResponseEntity<>(user, HttpStatus.OK);
//            }
        } catch (Exception e) {
            //log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return new ResponseEntity<>("User not found with given user id", HttpStatus.BAD_REQUEST);
    }
}
