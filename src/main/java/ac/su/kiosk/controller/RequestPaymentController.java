package ac.su.kiosk.controller;

import ac.su.kiosk.constant.PaymentStatus;
import ac.su.kiosk.domain.Admin;
import ac.su.kiosk.domain.OrderModuleDTO;
import ac.su.kiosk.domain.Store;
import ac.su.kiosk.dto.IAMPortDTO;
import ac.su.kiosk.repository.*;
import ac.su.kiosk.service.PaymentService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/request_payment")
public class RequestPaymentController {
    @Autowired
    private final PaymentService paymentService;
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private final StoreRepository storeRepository;
    @Autowired
    private final OrderModuleDTORepository orderModuleDTORepository;

    @GetMapping("/check_out/{adminId}")
    public OrderModuleDTO paymentPage(@PathVariable int adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        Store store = storeRepository.findByAdmin(admin).orElse(null);

        OrderModuleDTO orderModuleDTO = new OrderModuleDTO();
        orderModuleDTO.setStatus(PaymentStatus.READY);
        orderModuleDTO.setEmail(admin.getEmail());
        orderModuleDTO.setAddress(store.getLocation());
        orderModuleDTO.setStoreName(store.getName());
        orderModuleDTO.setOrderUid(UUID.randomUUID().toString());
        orderModuleDTO.setPrice(0L);
        return orderModuleDTORepository.save(orderModuleDTO);
    }

    @PostMapping("/check_out")
    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody IAMPortDTO request) {
        IamportResponse<Payment> iamportResponse = paymentService.paymentByCallback(request);
        log.info("결제 응답 = {}", iamportResponse.getResponse().toString());
        OrderModuleDTO orderModuleDTO = orderModuleDTORepository.findByOrderUid(request.getOrderUid()).get();
        orderModuleDTO.setStatus(PaymentStatus.OK);
        orderModuleDTO.setPaymentUid(request.getPaymentUid());
        orderModuleDTORepository.save(orderModuleDTO);
        return new ResponseEntity<>(iamportResponse, HttpStatus.OK);
    }
}