package com.api.pandora.model.request;

import com.api.pandora.model.user.UserDetails;
import lombok.Data;

@Data
public class OnboardUserRequest {
    private boolean newUser;
    private UserDetails userDetails;
}
