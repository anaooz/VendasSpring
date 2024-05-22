package io.github.anaooz.rest.dto;

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
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
