package com.freewheelin.mathflat.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Base {

    @CreationTimestamp
    private LocalDate cdate;
    @UpdateTimestamp
    private LocalDate udate;

    private String isDelete = "1";  //{0:Yes, 1:No}, 기본은 1

}