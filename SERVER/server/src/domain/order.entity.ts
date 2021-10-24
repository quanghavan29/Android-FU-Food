import { Column, Entity, ManyToOne } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { User } from "./user.entity";

@Entity('order')
export class Order extends BaseEntity {

    @ManyToOne(() => User)
    user?: User

    @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP'})
    orderedDate?: Date;

    @Column()
    address?: string;

    @Column()
    totalAmount?: number;

    @Column()
    totalQuantity?: number;
    
    @Column()
    totalItem?: number;

}