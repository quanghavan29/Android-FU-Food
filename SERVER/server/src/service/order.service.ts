import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { OrderDTO } from './dto/order.dto';
import { OrderMapper } from './mapper/order.mapper';
import { OrderRepository } from '../repository/order.repository';

@Injectable()
export class OrderService {
    constructor(@InjectRepository(OrderRepository) private orderRepository: OrderRepository) {}

    async findById(id: string): Promise<OrderDTO | undefined> {
        const result = await this.orderRepository.findOne({
            relations: ['user'],
            where: {
                id: id,
            }
        });
        return OrderMapper.fromEntityToDTO(result);
    }

    async save(orderDTO: OrderDTO): Promise<OrderDTO | undefined> {
        const newOrder = await this.orderRepository.save(OrderMapper.fromDTOtoEntity(orderDTO));
        return OrderMapper.fromEntityToDTO(newOrder);
    }

    async getListOrdersOfUser(userId: string): Promise<OrderDTO[] | undefined> {
        const result = await this.orderRepository.find({
            relations: ['user'],
            where: {
                user: {
                    id: userId,
                }
            },
            order: {
                orderedDate: 'DESC',
            }
        });

        const orderDTOs: OrderDTO[] = [];
        result.forEach(order => {
            orderDTOs.push(order);
        });

        return orderDTOs;
    }
    
}
