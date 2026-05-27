package com.jcallo.reto.webdto.response;

import lombok.Data;

@Data
public class BaseWebResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> BaseWebResponse<T> successWithData(T data) {
        BaseWebResponse<T> response = new BaseWebResponse<>();
        response.success = true;
        response.data = data;
        return response;
    }

    public static <T> BaseWebResponse<T> successNoData() {
        BaseWebResponse<T> response = new BaseWebResponse<>();
        response.success = true;
        response.message = "Operación exitosa";
        return response;
    }
}
