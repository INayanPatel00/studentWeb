package com.brevitaz.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StudentWebDTO {
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private int age;
    private String gender;


    private int houseNo;
    private String landMark;
    private String city;
    private String state;
    private int pinCode;

    private String departmentName;

    private String subjectName1;
    private String subjectName2;
    private String subjectName3;


}
