package com.api.pandora.model.response;

import com.api.pandora.model.user.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnboardUserResponse {

    private boolean isUserOnboarded;
    private UserDetails userDetails;
}
