package com.works.props;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductDto {

    private boolean status;
    private String message;
    @JsonIgnore
    private Object result;
    @JsonIgnore
    private Object errors;

}
