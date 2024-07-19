package ac.su.kiosk.controller;


import ac.su.kiosk.domain.Menu;
import ac.su.kiosk.domain.Order;
import ac.su.kiosk.domain.OrderItem;
import ac.su.kiosk.dto.OrderItemDTO;
import ac.su.kiosk.service.MenuService;
import ac.su.kiosk.service.OrderItemService;
import ac.su.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderitems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final MenuService menuService;


    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {

        OrderItem savedOrderItem = new OrderItem();

        Order targetOrder = orderService.findOrderByPaymentUid(orderItemDTO.getPaymentUid()).get();
        savedOrderItem.setOrder(targetOrder);

        Menu targetMenu = menuService.getMenuById(orderItemDTO.getMenuId()).get();
        savedOrderItem.setMenu(targetMenu);

        savedOrderItem.setQuantity(orderItemDTO.getQuantity());
        savedOrderItem.setPrice(orderItemDTO.getPrice());

        orderItemService.createOrderItem(savedOrderItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
