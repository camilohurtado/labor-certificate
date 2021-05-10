package com.intertecintl.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class User
{
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String password;
}
