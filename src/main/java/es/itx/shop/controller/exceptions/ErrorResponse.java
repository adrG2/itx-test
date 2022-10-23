package es.itx.shop.controller.exceptions;

public record ErrorResponse(
        ErrorCodeResponse codeResponse,
        String message
) {
}

