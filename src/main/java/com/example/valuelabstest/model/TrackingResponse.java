package com.example.valuelabstest.model;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponse {

    String tracking_number;
    String created_at;
}
