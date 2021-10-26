import { OrderItem } from '../../domain/OrderItem.entity';
import { OrderItemDTO } from '../dto/OrderItem.dto';

/**
 * An OrderItem mapper object.
 */
export class OrderItemMapper {
    static fromDTOtoEntity(orderitemDTO: OrderItemDTO): OrderItem {
        if (!orderitemDTO) {
            return;
        }
        const orderitem = new OrderItem();
        const fields = Object.getOwnPropertyNames(orderitemDTO);
        fields.forEach(field => {
            orderitem[field] = orderitemDTO[field];
        });

        return orderitem;
    }

    static fromEntityToDTO(orderitem: OrderItem): OrderItemDTO {
        if (!orderitem) {
            return;
        }
        const orderitemDTO = new OrderItemDTO();

        const fields = Object.getOwnPropertyNames(orderitem);

        fields.forEach(field => {
            orderitemDTO[field] = orderitem[field];
        });

        return orderitemDTO;
    }
}
