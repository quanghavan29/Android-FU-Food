import { Food } from "../domain/food.entity";
import { EntityRepository, Repository } from "typeorm";

@EntityRepository(Food)
export class FoodRepository extends Repository<Food>{}