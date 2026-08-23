package com.Api_Key_demo.Api_Key_demo.Controllers;

import com.Api_Key_demo.Api_Key_demo.UseCases.RemoveApiKeyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/remove")
public class RemoveApiKeyController {


    private final RemoveApiKeyUseCase useCase;


    public RemoveApiKeyController(RemoveApiKeyUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteApi(@RequestHeader(name = "userId") String userId , @RequestHeader(name = "api") String api){
        try {
            useCase.remove(userId, api);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Api Deleted");

        }catch (SecurityException s){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(s.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
