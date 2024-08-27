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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/orderComplete")
public class OrderCompleteController {
    private final OrderCompleteRepository orderCompleteRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
//    @GetMapping
//    public List<OrderCompleteDTO> getAllOrderComplete() {
//        // 완료되지 않은 주문 목록을 반환
//        List<OrderComplete> orderCompleteList =
//                orderCompleteRepository.findAllByComplete(false);
//        List<OrderCompleteDTO> orderCompleteDTOList = new ArrayList<>();
//
//        // 주문 목록을 DTO로 변환
//
//        for(OrderComplete orderComplete : orderCompleteList) {
//            OrderCompleteDTO orderCompleteDTO = new OrderCompleteDTO();
//
//            // OrderCompleteDTO에 OrderItemDTO를 담아서 반환
//
//            Order targetOrder = orderRepository.findById(orderComplete.getOrder().getId()).get();
//            List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(targetOrder.getId());
//
//            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
//
//            for (OrderItem orderItem : orderItems) {
//                OrderItemDTO orderItemDTO = new OrderItemDTO();
//
//                orderItemDTO.setMenuId(orderItem.getMenu().getId());
//                orderItemDTO.setQuantity(orderItem.getQuantity());
//                orderItemDTO.setPrice(orderItem.getPrice());
//                orderItemDTOList.add(orderItemDTO);
//            }
//            orderCompleteDTO.setId(targetOrder.getId());
//            orderCompleteDTO.setOrderItemList(orderItemDTOList);
//            orderCompleteDTO.setComplete(orderComplete.getComplete());
//            orderCompleteDTO.setDatetime(targetOrder.getDateTime());
//
//
//            orderCompleteDTOList.add(orderCompleteDTO);
//        }
//        return orderCompleteDTOList;
//    }

    @GetMapping
    public List<OrderCompleteDTO> getAllOrderComplete() {
        // 완료되지 않은 주문 목록을 반환
        List<OrderComplete> orderCompleteList =
                // 해결
                orderCompleteRepository.findAllByComplete(false);
        List<OrderCompleteDTO> orderCompleteDTOList = new ArrayList<>();

        List<Order> targetOrderList = new ArrayList<>();
        for (OrderComplete orderComplete : orderCompleteList) {
//            targetOrderList.add(orderRepository.findById(orderComplete.getOrder().getId()).get());
            targetOrderList.add(orderComplete.getOrder());
        }

        List<OrderItem> targetOrderItem = orderItemRepository.findAllByOrderIdIn(targetOrderList.stream().map(Order::getId).toList());
        Map<Order, List<OrderItem>> orderItemMap = targetOrderItem.stream().collect(Collectors.groupingBy(OrderItem::getOrder));
        Set<Order> orderItemKeyset = orderItemMap.keySet();

        for (Order x : new ArrayList<>(orderItemKeyset)) {
            OrderCompleteDTO orderCompleteDTO = new OrderCompleteDTO();
            orderCompleteDTO.setOrderId(x.getId());

            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
            for (OrderItem orderItem : orderItemMap.get(x)) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setMenuId(orderItem.getMenu().getId());
                orderItemDTO.setQuantity(orderItem.getQuantity());
                orderItemDTO.setPrice(orderItem.getPrice());
                orderItemDTOList.add(orderItemDTO);
            }
            orderCompleteDTO.setId(x.getId());
            orderCompleteDTO.setOrderItemList(orderItemDTOList);
            orderCompleteDTO.setComplete(false);
            orderCompleteDTO.setDatetime(x.getDateTime());
            orderCompleteDTOList.add(orderCompleteDTO);
        }
        return orderCompleteDTOList;
    }

    @PostMapping
    public ResponseEntity<String> completeOrder(@RequestBody Map<String, Long> request) {
        Long id = request.get("orderId"); // orderId가 들어옴.
        List<OrderComplete> targetOC = orderCompleteRepository.findAllByOrderId(id);
        for (OrderComplete x : orderCompleteRepository.findAllByOrderId(id)) {
            x.setComplete(true);
            orderCompleteRepository.save(x);
        }

        orderCompleteRepository.updateTrueById(id);
        return ResponseEntity.ok("주문 완료 처리 완료");
    }
}
