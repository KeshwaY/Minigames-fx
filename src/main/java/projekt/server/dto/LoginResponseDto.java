package projekt.server.dto;

public class LoginResponseDto extends AbstractDto {
    private boolean success;

    public LoginResponseDto(Boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
