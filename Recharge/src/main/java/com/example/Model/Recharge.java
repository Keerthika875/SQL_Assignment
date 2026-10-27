package com.example.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {

    private Long rechargeId;

    @Pattern(
        regexp = "[0-9]{10}",
        message = "Mobile number must contain 10 digits"
    )
    private String mobileNumber;

    @NotBlank(message = "Operator is required")
    private String operator;

    @Min(value = 10, message = "Minimum recharge amount is 10")
    private int amount;

    @NotBlank(message = "Plan type is required")
    private String planType;
}