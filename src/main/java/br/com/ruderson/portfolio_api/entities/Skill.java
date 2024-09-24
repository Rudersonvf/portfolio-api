package br.com.ruderson.portfolio_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name ="tb_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String iconUrl;
    private String docUrl;
    private Integer level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skills = (Skill) o;
        return Objects.equals(id, skills.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
