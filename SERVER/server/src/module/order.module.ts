import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { OrderService } from "../service/order.service";
import { OrderController } from "../web/rest/order.controller";
import { OrderRepository } from "../repository/order.repository";
import { ManagementController } from '../web/rest/management.controller';
import { OrderItemService } from "../service/orderitem.service";
import { OrderItemRepository } from "../repository/orderitem.repository";

@Module({
    imports: [
        TypeOrmModule.forFeature([OrderRepository, OrderItemRepository]),
    ],
    controllers: [OrderController, ManagementController],
    providers: [OrderService, OrderItemService],
    exports: [OrderService, OrderItemService],
})
export class OrderModule {}
