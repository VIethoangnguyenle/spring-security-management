package com.hoang.springsecuritylearn.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.*;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestError {

    public static final String EMPTY = "required";
    public static final String INVALID = "invalid";
    public static final String USED = "used";
    public static final String BLOCKED = "blocked";

    private Map<String, Object> errors;
    private String message;

    public RestError() {
    }

    public RestError(Map<String, Object> toMap, String message) {
        this.errors.putAll(toMap);
        this.message = message;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Map<String, List<String>> errors) {
        return new Builder(errors);
    }

    public static class Builder {
        private Map<String, List<String>> errors = new HashMap<>();
        private String message;

        public Builder() {
        }

        public Builder(Map<String, List<String>> errors) {
            this.errors.putAll(errors);
        }

        public Builder addMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder addInvalidField(String field) {
            return this.addErrorField(field, Collections.singletonList(INVALID));
        }

        public Builder addBlockField(String field) {
            return this.addErrorField(field, Collections.singletonList(BLOCKED));
        }

        public Builder addUsedField(String field) {
            return this.addErrorField(field, Collections.singletonList(USED));
        }

        public Builder addEmptyField(String field) {
            return this.addErrorField(field, Collections.singletonList(EMPTY));
        }

        public Builder addErrorField(String field, List<String> errors) {
            this.errors.put(field, errors);
            return this;
        }

        public RestError build() {
            JSONObject json = new JSONObject();

            for (Map.Entry<String, List<String>> error : errors.entrySet()) {
                String[] keyLevels = error.getKey().split("\\.");
                JSONObject innerJson = json;

                for (int i = 0; i < keyLevels.length; i++) {
                    if (!innerJson.has(keyLevels[i])) {
                        innerJson.put(keyLevels[i], new JSONObject());
                        if (i == keyLevels.length - 1) {
                            innerJson.put(keyLevels[i], error.getValue());
                            break;
                        }
                    }
                    innerJson = innerJson.getJSONObject(keyLevels[i]);
                }
            }
            return new RestError(json.toMap(), message);
        }
    }
}
