package com.Api_Key_demo.Api_Key_demo.Controllers;

import com.Api_Key_demo.Api_Key_demo.UseCases.RegenerateApiKeyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/regenerate")
public class RegenerateApiKeyController {

    private final RegenerateApiKeyUseCase useCase;


    public RegenerateApiKeyController(RegenerateApiKeyUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<?> regenerate(@RequestHeader(name = "userId") String userId){
        try {
            return ResponseEntity.ok(useCase.regenerate(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
