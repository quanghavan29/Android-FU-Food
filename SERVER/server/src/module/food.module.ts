import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { FoodService } from "../service/food.service";
import { FoodController } from "../web/rest/food.controller";
import { FoodRepository } from "../repository/food.repository";
import { ManagementController } from '../web/rest/management.controller';

@Module({
    imports: [
        TypeOrmModule.forFeature([FoodRepository]),
    ],
    controllers: [FoodController, ManagementController],
    providers: [FoodService],
    exports: [FoodService],
})
export class FoodModule {}
