package io.github.anaooz.domain.repository;

import io.github.anaooz.domain.entity.Cliente;
import io.github.anaooz.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT p from Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
