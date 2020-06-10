package pl.wiktor.demo.domain.exception;

public enum ExceptionCode {
    //technical errors
    INTERNAL_SERVER("CS_T_001", "Internal Server Exception", 500),
    ERROR_PARSING_REQUEST("CS_T_002", "Error while parsing request", 401),

    //business errors
    INVALID_NAME("CS_B_001", "Provided name '%s' is invalid", 400),
    INVALID_CATEGORY("CS_B_002", "Provided category '%s' is invalid", 400),
    INVALID_CONTENT_ID("CS_B_003", "Provided id '%s' hasn't been found", 400),
    INVALID_PRICE("CS_B_004", "Provided price '%f' is invalid", 400),
    INVALID_NOTIONAL("CS_B_005", "Provided notional '%f' is invalid", 400),
    INVALID_CLIENT_NAME("CS_B_006", "Provided client name '%f' is invalid", 400);

    private String code;
    private String detailsPattern;
    private int httpStatus;

    ExceptionCode(String code, String detailPattern, Integer httpStatus) {
        this.code = code;
        this.detailsPattern = detailPattern;
        this.httpStatus = httpStatus;
    }


    public String getCode() {
        return code;
    }

    public String getDetailsPattern() {
        return detailsPattern;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}
