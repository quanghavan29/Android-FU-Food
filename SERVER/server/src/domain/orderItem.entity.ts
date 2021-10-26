import { Column, Entity, ManyToOne } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { Food } from "./food.entity";
import { Order } from "./order.entity";

@Entity('order_item')
export class OrderItem extends BaseEntity {

    @ManyToOne(() => Order)
    order?: Order

    @ManyToOne(() => Food)
    food?: Food

    @Column()
    subAmount?: number;

    @Column()
    quantity?: number;

}