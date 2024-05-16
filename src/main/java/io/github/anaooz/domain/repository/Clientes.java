package io.github.anaooz.domain.repository;

import io.github.anaooz.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT * FROM Cliente c WHERE c.nome like '%:nome%'", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "DELETE FROM Cliente c WHERE c.nome = :nome")
    @Modifying //permissão p/ atualizar tabela
    void deleteByName(String nome);

    boolean existsByNome(String nome);

    @Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);



    //convenção já cria a consulta pra você
    //List<Cliente> findByNomeLike(String nome);
    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
}
