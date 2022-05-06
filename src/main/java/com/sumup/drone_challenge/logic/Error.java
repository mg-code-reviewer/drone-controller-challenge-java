package com.sumup.drone_challenge.logic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class Error {

    @Schema(name = "code", type = "integer", format = "int32", example = "1001")
    @JsonProperty("code")
    private int code;

    @Schema(name = "description", type = "string", example = "Error description")
    @JsonProperty("description")
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "detail", type = "integer", example = "Error detail")
    @JsonProperty("detail")
    private String detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> stack;

    public Error() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return code == error.code && Objects.equals(description, error.description) && Objects.equals(detail, error.detail) && Objects.equals(stack, error.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, detail, stack);
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", stack=" + stack +
                '}';
    }
}
