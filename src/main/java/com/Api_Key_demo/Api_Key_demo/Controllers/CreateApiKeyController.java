package com.Api_Key_demo.Api_Key_demo.Controllers;

import com.Api_Key_demo.Api_Key_demo.DTO.Requests.CreateApiKeyRequest;
import com.Api_Key_demo.Api_Key_demo.Ports.CreateApiKeyPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/create")
public class CreateApiKeyController {

    private final CreateApiKeyPort port;

    public CreateApiKeyController(CreateApiKeyPort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateApiKeyRequest request){
        try {
            return ResponseEntity.ok(port.create(request.userId(),request.offer()));
        } catch (Exception e) {
            return ResponseEntity.ofNullable(e.getMessage());
        }
    }


}
