package com.likelion.bliond.web.poll;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
public class ApiPollControllerV1Test {

}
