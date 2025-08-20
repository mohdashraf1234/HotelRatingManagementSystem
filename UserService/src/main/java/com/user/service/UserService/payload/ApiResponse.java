package com.user.service.UserService.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

    public ApiResponse() { }

    public ApiResponse(String message, boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    // getters
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
    public HttpStatus getStatus() { return status; }

    // setters
    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setStatus(HttpStatus status) { this.status = status; }
}


/*package com.user.service.UserService.payload;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}*/
