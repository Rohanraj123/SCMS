package org.manage.scms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInResponse
{
    private String token;
    private Long ExpiresIn;
}
