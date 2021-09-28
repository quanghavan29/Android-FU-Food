import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { FoodCategoryService } from "../service/foodCategory.service";
import { FoodCategoryController } from "../web/rest/foodCategory.controller";
import { FoodCategoryRepository } from "../repository/foodCategory.repository";
import { ManagementController } from '../web/rest/management.controller';

@Module({
    imports: [
        TypeOrmModule.forFeature([FoodCategoryRepository]),
    ],
    controllers: [FoodCategoryController, ManagementController],
    providers: [FoodCategoryService],
    exports: [FoodCategoryService],
})
export class FoodCategoryModule {}
