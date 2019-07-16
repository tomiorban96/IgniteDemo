package com.ignite.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="The cache is inconsistent")
public class InconsistentCacheException extends RuntimeException {
}
