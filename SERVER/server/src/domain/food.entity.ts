import { Column, Entity, ManyToOne } from "typeorm";
import { BaseEntity } from "./base/base.entity";
import { FoodCategory } from "./foodCategory.entity";
import { Restaurant } from "./restaurant.entity";

@Entity('food')
export class Food extends BaseEntity{

    @Column({ nullable: true })
    name?: string;

    @Column({ nullable: true })
    imageUrl?: string;

    @Column({ nullable: true })
    price?: number;

    @Column({ nullable: true })
    salesQuantity?: number;

    @ManyToOne(() => FoodCategory, foodCategory => foodCategory.foods)
    foodCategory?: FoodCategory;

    @ManyToOne(() => Restaurant, restaurant => restaurant.foods)
    restaurant?: Restaurant;

    @Column({ nullable: true, type: 'float' })
    reviewPoint?: number;

    @Column({ nullable: true })
    numberOfReview?: number;
    
}