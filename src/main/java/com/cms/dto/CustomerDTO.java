package com.cms.dto;

import com.cms.annotation.ValidNationalCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "First name is necessary field")
    private String firstName;

    @NotBlank(message = "Last name is necessary field")
    private String lastName;

    //    @Email
    @Pattern(regexp = "^[A-Za-z0-9._+%-]+@gmail\\.com$", message = "Your pattern email should ...@gmail.com ")
    private String email;

    @ValidNationalCode(message = "Please check your national code number.")
    private String nationalCode;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private String createdBy;

    private String updatedBy;

}
