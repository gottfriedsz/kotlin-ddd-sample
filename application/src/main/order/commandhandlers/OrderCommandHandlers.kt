package order.commandhandlers

import kotlinddd.domain.order.Order
import kotlinddd.domain.order.OrderRepository
import kotlinddd.domain.order.payment.PaymentService
import order.commandhandlers.commands.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.EventBus
import java.util.*

class OrderCommandHandlers(private val repository: OrderRepository,
                                private val paymentService: PaymentService,
                                private val eventBus: EventBus) {
    @CommandHandler
    fun createOrder(command: CreateOrderCommand): UUID {
        val orderId = UUID.randomUUID()
        val customer = repository.findCustomerById(command.customerId)

        val order = Order(orderId, customer)
        repository.save(order)

        return orderId
    }

    @CommandHandler
    fun addProduct(command: AddProductCommand) {
        val order = repository.findById(command.orderId)
        val product = repository.findProductById(command.productId)

        order.addProduct(product, command.quantity)

        repository.save(order)
    }

    @CommandHandler
    fun changeProductQuantity(command: ChangeProductQuantityCommand) {
        val order = repository.findById(command.orderId)
        val product = repository.findProductById(command.productId)

        order.changeProductQuantity(product, command.quantity)

        repository.save(order)
    }

    @CommandHandler
    fun removeProduct(command: RemoveProductCommand) {
        val order = repository.findById(command.orderId)
        val product = repository.findProductById(command.productId)

        order.removeProduct(product)

        repository.save(order)
    }

    @CommandHandler
    fun payOrder(command: PayOrderCommand) {
        val order = repository.findById(command.orderId)

        order.pay(command.creditCard, paymentService, eventBus) //TODO provide global access to eventBus

        repository.save(order)
    }
}

