package com.bada.bazaar.error;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse  {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


}
