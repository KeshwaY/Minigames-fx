package projekt.server.dto;

public class RegisterResponseDto extends AbstractDto {
    public boolean success;

    public RegisterResponseDto(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
