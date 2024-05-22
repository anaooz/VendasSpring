package io.github.anaooz.service;

import io.github.anaooz.domain.entity.Pedido;
import io.github.anaooz.domain.enums.StatusPedido;
import io.github.anaooz.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
