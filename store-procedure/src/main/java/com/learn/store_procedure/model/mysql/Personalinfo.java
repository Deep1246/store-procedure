package com.learn.store_procedure.model.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personalinfo {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Date birthDate;

    private Long sex;

    private String created_by;

    private String createDate;

    private String updateBy;

    private String updateDate;

}
