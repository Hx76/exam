package com.onlineexam.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientInfo {
    String ip;
    String OS;
    String clientType;
    private static ClientInfo instance;


    public static synchronized ClientInfo getInstance() {
        if (instance == null) {
            instance = new ClientInfo();
        }
        return instance;
    }

}
