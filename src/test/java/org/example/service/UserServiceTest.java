package org.example.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceTest {
    String testName = "Andrew";
    String testLastName = "Bobrov";
    byte testAge = 15;
}
