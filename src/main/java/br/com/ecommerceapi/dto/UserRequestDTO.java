package br.com.ecommerceapi.dto;

import br.com.ecommerceapi.entity.validation.UniqueLogin;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
@ApiModel(value="User")
public class UserRequestDTO {

    @NotBlank(message = "{null.field}")
    private String firstName;

    @NotBlank(message = "{null.field}")
    private String lastName;

    @NotNull(message = "{null.field}")
    private LocalDate birthday;

    @NotBlank(message = "{null.field}")
    @UniqueLogin
    private String login;

    @NotBlank(message = "{null.field}")
    private String phone;

    @NotBlank(message = "{null.field}")
    @Email(message = "{invalid.field}")
    private String email;

    @NotBlank(message = "{null.field}")
    private String password;


}
