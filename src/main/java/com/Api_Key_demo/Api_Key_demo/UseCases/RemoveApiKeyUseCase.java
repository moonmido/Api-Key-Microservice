package com.Api_Key_demo.Api_Key_demo.UseCases;

import com.Api_Key_demo.Api_Key_demo.Ports.GetUserByApiKeyPort;
import com.Api_Key_demo.Api_Key_demo.Ports.RemoveApiKeyPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component

public class RemoveApiKeyUseCase {

    private final RemoveApiKeyPort removeApiKeyPort;
    private final GetUserByApiKeyPort getUserByApiKeyPort;

    public RemoveApiKeyUseCase(RemoveApiKeyPort removeApiKeyPort, GetUserByApiKeyPort getUserByApiKeyPort) {
        this.removeApiKeyPort = removeApiKeyPort;
        this.getUserByApiKeyPort = getUserByApiKeyPort;
    }

    @Transactional
    public void remove(String userId , String apiKey){
        if(userId==null || apiKey==null) throw new IllegalArgumentException("Invalid userId or ApiKey");

        try {
            String userIdByApiKey = getUserByApiKeyPort.getUserIdByApiKey(apiKey);
            if(!userIdByApiKey.equals(userId)) throw new SecurityException("Api Not for this Authenticated User : "+userId);
            String encodedApiKey = Base64.getEncoder()
                    .encodeToString(
                            apiKey.getBytes(StandardCharsets.UTF_8)
                    );

            removeApiKeyPort.remove(userId, encodedApiKey);
        } catch (Exception e) {
            throw e;
        }
    }

}
