package ac.su.kiosk.controller;

import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderComplete;
import ac.su.kiosk.domain.OrderItem;
import ac.su.kiosk.dto.OrderCompleteDTO;
import ac.su.kiosk.dto.OrderItemDTO;
import ac.su.kiosk.repository.OrderCompleteRepository;
import ac.su.kiosk.repository.OrderItemRepository;
import ac.su.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/orderComplete")
public class OrderCompleteController {
    private final OrderCompleteRepository orderCompleteRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @GetMapping
    public List<OrderCompleteDTO> getAllOrderComplete() {
        // 완료되지 않은 주문 목록을 반환
        List<OrderComplete> orderCompleteList =
                orderCompleteRepository.findAllByComplete(false);
        List<OrderCompleteDTO> orderCompleteDTOList = new ArrayList<>();

        // 주문 목록을 DTO로 변환

        for(OrderComplete orderComplete : orderCompleteList) {
            OrderCompleteDTO orderCompleteDTO = new OrderCompleteDTO();

            // OrderCompleteDTO에 OrderItemDTO를 담아서 반환

            Order targetOrder = orderRepository.findById(orderComplete.getOrder().getId()).get();
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(targetOrder.getId());

            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

            for (OrderItem orderItem : orderItems) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();

                orderItemDTO.setMenuId(orderItem.getMenu().getId());
                orderItemDTO.setQuantity(orderItem.getQuantity());
                orderItemDTO.setPrice(orderItem.getPrice());
                orderItemDTOList.add(orderItemDTO);
            }
            orderCompleteDTO.setId(targetOrder.getId());
            orderCompleteDTO.setOrderItemList(orderItemDTOList);
            orderCompleteDTO.setComplete(orderComplete.getComplete());
            orderCompleteDTO.setDatetime(targetOrder.getDateTime());


            orderCompleteDTOList.add(orderCompleteDTO);
        }
        return orderCompleteDTOList;
    }
}
