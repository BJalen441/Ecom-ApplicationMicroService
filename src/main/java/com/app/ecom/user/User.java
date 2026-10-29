package com.app.ecom.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "app_user")
@Data //Works as Getter, Setter, and RequiredArgsConstructor
@NoArgsConstructor //Gens Constructor as well, but one with no arguments
@AllArgsConstructor // Passes all User fields
public class User { //Now a JPA Entity with @Table
    @Id //Marks id as an PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gens unique ID
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    //Optional entry
    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
