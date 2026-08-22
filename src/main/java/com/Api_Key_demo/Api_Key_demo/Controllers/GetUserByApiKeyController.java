package com.Api_Key_demo.Api_Key_demo.Controllers;

import com.Api_Key_demo.Api_Key_demo.Ports.GetUserByApiKeyPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get")
public class GetUserByApiKeyController {

    private final GetUserByApiKeyPort port;

    public GetUserByApiKeyController(GetUserByApiKeyPort port) {
        this.port = port;
    }

    @GetMapping
    public ResponseEntity<?> getUserId(@RequestHeader(name = "api") String api){
        try {
            return ResponseEntity.ok(port.getUserIdByApiKey(api));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}
