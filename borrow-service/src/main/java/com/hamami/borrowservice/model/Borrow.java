package com.hamami.borrowservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Borrow {

    @Id
    private Long id;
    private String title;
    private boolean isBorrowed;
    private LocalDate dueDate;

    public Borrow() {
    }

    public Borrow(Long id, String title) {
        this.id = id;
        this.title = title;
    }


}
