package com.example.javanoo6.webpart.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class PlayerSaveException(msg: String) : RuntimeException(msg)
