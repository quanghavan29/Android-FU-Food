import { FoodCategory } from "../domain/foodCategory.entity";
import { EntityRepository, Repository } from "typeorm";

@EntityRepository(FoodCategory)
export class FoodCategoryRepository extends Repository<FoodCategory>{}