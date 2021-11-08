import { Food } from '../domain/food.entity';
import { Order } from '../domain/order.entity';
import { EntityRepository, getConnection, Repository } from 'typeorm';
import { OrderItem } from '../domain/orderItem.entity';

@EntityRepository(OrderItem)
export class OrderItemRepository extends Repository<OrderItem> {
    async findOrderDetailsByUser(userId: string, orderId: string): Promise<any[] | undefined> {
        const result = await getConnection()
        .createQueryBuilder(OrderItem, 'order_item')
        .select('food.imageUrl as imageFood, food.name as foodName')
        .addSelect('order_item.quantity as subQuantity, order_item.subAmount as subAmount')
        .innerJoin(Order, 'order', 'order.id = order_item.orderId')
        .innerJoin(Food, 'food', 'food.id = order_item.foodId')
        .where('order.userId = :userId', { userId: userId })
        .andWhere('order.id = :orderId', { orderId: orderId })
        .getRawMany();

        return result;
    }

    async findOrderDetail(orderId: string): Promise<any[] | undefined> {
        const result = await getConnection()
        .createQueryBuilder(OrderItem, 'order_item')
        .select('food.imageUrl as imageFood, food.name as foodName')
        .addSelect('order_item.quantity as subQuantity, order_item.subAmount as subAmount')
        .innerJoin(Order, 'order', 'order.id = order_item.orderId')
        .innerJoin(Food, 'food', 'food.id = order_item.foodId')
        .where('order.id = :orderId', { orderId: orderId })
        .getRawMany();

        return result;
    }
}
