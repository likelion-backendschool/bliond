package com.likelion.bliond.web.response;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;
    private List<T> data;


    public static <T> ApiResponse<T> ok(List<T> data) {
        return new ApiResponse<>(OK.value(), data);
    }
    public static <T> ApiResponse<T> ok(T data) {
        List<T> result = new ArrayList<>();
        result.add(data);
        return new ApiResponse<>(OK.value(), result);
    }

    public static <T> ApiResponse<T> created(T data) {
        List<T> result = new ArrayList<>();
        result.add(data);
        return new ApiResponse<>(CREATED.value(), result);
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(OK.value(), null);
    }
}
