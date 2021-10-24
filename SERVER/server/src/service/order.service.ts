import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { OrderDTO } from './dto/order.dto';
import { OrderMapper } from './mapper/order.mapper';
import { OrderRepository } from '../repository/order.repository';

@Injectable()
export class OrderService {
    constructor(@InjectRepository(OrderRepository) private orderRepository: OrderRepository) {}

    async save(orderDTO: OrderDTO): Promise<OrderDTO | undefined> {
        const createdOrder = await this.orderRepository.save(OrderMapper.fromDTOtoEntity(orderDTO));
        return OrderMapper.fromEntityToDTO(createdOrder);
    }
    
}
