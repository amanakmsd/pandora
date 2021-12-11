package com.api.pandora.model.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetWalletFundsRequest {
    String userId;
}
