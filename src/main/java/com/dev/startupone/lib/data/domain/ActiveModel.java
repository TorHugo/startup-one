package com.dev.startupone.lib.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "active_tb")
public class ActiveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "active_id")
    private Long id;

    private String name;
    private String description;
    private String category;
    private String timeOffer;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
