package br.com.ruderson.portfolio_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name ="tb_education")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String institution;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer workload;
    private String certificateUrl;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(id, education.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
