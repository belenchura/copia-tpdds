package server.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Boolean status;
    public LoginResponse(Boolean status){
        this.status=status;
    }
}
