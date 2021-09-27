import { Column, Entity } from "typeorm";
import { BaseEntity } from "./base/base.entity";

@Entity('food_category')
export class FoodCategory extends BaseEntity{

    @Column({ nullable: true })
    name;

    @Column({ nullable: true })
    image;
    
}