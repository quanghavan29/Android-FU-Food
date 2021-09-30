import { Column, Entity, OneToMany } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { Food } from "./food.entity";

@Entity('food_category')
export class FoodCategory extends BaseEntity{

    @Column({ nullable: true })
    name?: string;

    @Column({ nullable: true })
    imageUrl?: string;

    @OneToMany(() => Food, food => food.foodCategory)
    foods?: Food[];
    
}