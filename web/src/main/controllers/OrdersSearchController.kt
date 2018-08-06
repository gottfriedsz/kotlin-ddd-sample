package kotlinddd.web.controllers

import kotlinddd.application.order.commandhandlers.commands.*
import kotlinddd.domain.BusinessException
import kotlinddd.domain.order.payment.CreditCard
import kotlinddd.infrastructure.queries.OrdersQuery
import kotlinddd.infrastructure.queries.dtos.OrderDTO
import kotlinddd.infrastructure.queries.dtos.OrderPerUsersDTO
import kotlinddd.web.models.AddProductRequest
import kotlinddd.web.models.ChangeProductQuantityRequest
import kotlinddd.web.models.CreateOrderRequest
import kotlinddd.web.models.PayOrderRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderSearchController() {

    // Queries
    @GetMapping("/orders/{orderId}")
    fun findOrderById(@PathVariable("orderId") orderId: String): OrderDTO {
        return OrdersQuery.findOrderById(UUID.fromString(orderId))
    }

    @GetMapping("/orders", params = ["orders_per_users"])
    fun findOrderPerUsers(): List<OrderPerUsersDTO> {
        return OrdersQuery.findOrderPerUsers()
    }

    @GetMapping("/orders", params = ["last_orders"])
    fun findLastOrders(): List<Any> {
        return OrdersQuery.findLastOrders()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException() {
        return
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleGenerallException() {
        return
    }
}