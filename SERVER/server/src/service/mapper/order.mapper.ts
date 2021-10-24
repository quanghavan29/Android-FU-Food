import { Order } from '../../domain/Order.entity';
import { OrderDTO } from '../dto/Order.dto';

/**
 * An Order mapper object.
 */
export class OrderMapper {
    static fromDTOtoEntity(orderDTO: OrderDTO): Order {
        if (!orderDTO) {
            return;
        }
        const order = new Order();
        const fields = Object.getOwnPropertyNames(orderDTO);
        fields.forEach(field => {
            order[field] = orderDTO[field];
        });

        return order;
    }

    static fromEntityToDTO(order: Order): OrderDTO {
        if (!order) {
            return;
        }
        const orderDTO = new OrderDTO();

        const fields = Object.getOwnPropertyNames(order);

        fields.forEach(field => {
            orderDTO[field] = order[field];
        });

        return orderDTO;
    }
}
