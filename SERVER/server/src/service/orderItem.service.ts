import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { OrderItemDTO } from './dto/orderitem.dto';
import { OrderItemMapper } from './mapper/orderitem.mapper';
import { OrderItemRepository } from '../repository/orderitem.repository';
import { Order } from '../domain/order.entity';
import { OrderService } from './order.service';

@Injectable()
export class OrderItemService {
    constructor(@InjectRepository(OrderItemRepository) private orderitemRepository: OrderItemRepository,
        private readonly orderService: OrderService) {}

    async save(orderitemDTO: OrderItemDTO): Promise<OrderItemDTO | undefined> {
        const newOrderItem = await this.orderitemRepository.save(OrderItemMapper.fromDTOtoEntity(orderitemDTO));
        return OrderItemMapper.fromEntityToDTO(newOrderItem);
    }

    async saveAllItemOfOrder(carts: any[], order: Order): Promise<any | undefined> {
        for (let cart of carts) {
            let orderItemDTO: OrderItemDTO = {
                order: order,
                food: cart.food,
                subAmount: cart.food.price * cart.quantity,
                quantity: cart.quantity,
            }

            await this.save(orderItemDTO);
        }
    }

    async getOrdersDetailByUser(userId: string, orderId: string): Promise<any | undefined> {
        let order = await this.orderService.findById(orderId);
        let orderItems = await this.orderitemRepository.findOrderDetailsByUser(userId, orderId);
        return {
            order: order,
            orderItems: orderItems,
        };
    }
    
}
