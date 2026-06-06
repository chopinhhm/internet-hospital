package com.hospital.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单服务
 * 
 * 轻量级状态机管理订单生命周期：
 * PENDING -> ACCEPTED -> IN_PROGRESS -> COMPLETED
 */
@Slf4j
@Service
public class OrderService {

    public enum OrderState { PENDING, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED }

    public void createOrder(String patientId, String doctorId) {
        log.info("创建问诊订单: patient={}, doctor={}", patientId, doctorId);
    }

    public void acceptOrder(String orderId) {
        log.info("医生接诊: orderId={}", orderId);
    }

    public void completeOrder(String orderId) {
        log.info("问诊结束: orderId={}", orderId);
    }
}
