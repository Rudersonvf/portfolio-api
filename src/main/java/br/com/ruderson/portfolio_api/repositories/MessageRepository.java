package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.projections.MessageSummaryProjection;
import br.com.ruderson.portfolio_api.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value =
    "SELECT tb_message.id AS id, tb_message.name AS name, tb_message.subject AS subject, " +
            "tb_message.is_read AS isRead " +
            "FROM tb_message")
    List<MessageSummaryProjection>findAllProjectedBy();
}
