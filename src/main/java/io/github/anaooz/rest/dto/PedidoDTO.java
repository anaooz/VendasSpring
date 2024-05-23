package io.github.anaooz.rest.dto;

import io.github.anaooz.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

//{
//        "cliente": 1,
//        "total": 100,
//        "items": [
//            {
//            "produto": 1,
//            "quantidade": 10
//            }
//        ]
//}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO { //classe pra mapear objeto
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;
}
