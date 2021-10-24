import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { OrderService } from "../service/order.service";
import { OrderController } from "../web/rest/order.controller";
import { OrderRepository } from "../repository/order.repository";
import { ManagementController } from '../web/rest/management.controller';

@Module({
    imports: [
        TypeOrmModule.forFeature([OrderRepository]),
    ],
    controllers: [OrderController, ManagementController],
    providers: [OrderService],
    exports: [OrderService],
})
export class OrderModule {}
