package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.entities.User;
import br.com.ruderson.portfolio_api.dto.user.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(nativeQuery = true, value =
            "SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority " +
                    "FROM tb_user " +
                    "INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id " +
                    "INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id " +
                    "WHERE tb_user.email = :emailParam")
    List<UserDetailsProjection> searchUserAndRolesByEmail(@Param("emailParam") String email);
}