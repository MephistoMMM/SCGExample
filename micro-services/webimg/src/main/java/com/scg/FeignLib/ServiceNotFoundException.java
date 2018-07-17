package com.scg.FeignLib;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "service not found")
public class ServiceNotFoundException extends RuntimeException {}
