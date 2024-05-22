package io.github.anaooz.service.impl;

import io.github.anaooz.domain.entity.Cliente;
import io.github.anaooz.domain.entity.ItemPedido;
import io.github.anaooz.domain.entity.Pedido;
import io.github.anaooz.domain.entity.Produto;
import io.github.anaooz.domain.enums.StatusPedido;
import io.github.anaooz.domain.repository.Clientes;
import io.github.anaooz.domain.repository.ItemsPedido;
import io.github.anaooz.domain.repository.Pedidos;
import io.github.anaooz.domain.repository.Produtos;
import io.github.anaooz.exception.PedidoNaoEncontradoException;
import io.github.anaooz.exception.RegraNegocioException;
import io.github.anaooz.rest.dto.ItemPedidoDTO;
import io.github.anaooz.rest.dto.PedidoDTO;
import io.github.anaooz.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService { //camada de regra de negócio

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional //ou realiza todos os CRUDs, ou não realiza nenhum
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);
        //criamos pedido, mas não o salvamos (= !id)

        //itens com o pedido ainda não salvo
        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());

        //salva e obtém id
        repository.save(pedido);

        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(PedidoNaoEncontradoException::new);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) throw new RegraNegocioException("Não é possível realizar um pedido sem items");

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido; //transformou de dto -> itemPedido
                }).collect(Collectors.toList());
    }
}
