package com.caetano.demo.exceptions;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}
