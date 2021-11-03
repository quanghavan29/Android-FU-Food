import { Column, Entity, ManyToOne, PrimaryColumn } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { User } from "./user.entity";

@Entity('orders')
export class Order {

    @PrimaryColumn({default: (new Date()).getTime().toString()})
    id?: string;

    @Column({ nullable: true })
    createdBy?: string;
    @Column({ nullable: true, type: 'timestamp', default: () => 'CURRENT_TIMESTAMP'})
    createdDate?: Date;
    @Column({ nullable: true })
    lastModifiedBy?: string;
    @Column({ nullable: true, type: 'timestamp', default: () => 'CURRENT_TIMESTAMP'})
    lastModifiedDate?: Date;

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

    @Column()
    status?: string;

}