import { Column, Entity, ManyToOne, OneToMany } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { Food } from "./food.entity";

@Entity('restaurant')
export class Restaurant extends BaseEntity{

    @Column({ nullable: true })
    name?: string;

    @Column({ nullable: true })
    imageUrl?: string;

    @Column({ nullable: true })
    address?: string;

    @OneToMany(() => Food, food => food.restaurant)
    foods?: Food[];
    
}