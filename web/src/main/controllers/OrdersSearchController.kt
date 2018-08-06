package controllers

import kotlinddd.domain.BusinessException
import queries.OrdersQuery
import queries.dtos.OrderDTO
import queries.dtos.OrderPerUsersDTO
import org.springframework.http.HttpStatus
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