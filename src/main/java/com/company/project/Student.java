package com.company.project;

import lombok.*;

import java.io.File;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String
            firstName,
            lastName,
            email,
            phoneNumber,
            address,
            subject,
            state,
            city;

    private Gender gender;
    private Hobby hobby;
    private File picture;
    private DateOfBirth dateOfBirth;
}
