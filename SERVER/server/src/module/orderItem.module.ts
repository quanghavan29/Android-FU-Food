import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { OrderItemService } from "../service/orderitem.service";
import { OrderItemController } from "../web/rest/orderitem.controller";
import { OrderItemRepository } from "../repository/orderitem.repository";
import { ManagementController } from '../web/rest/management.controller';
import { OrderService } from "../service/order.service";
import { OrderRepository } from "../repository/order.repository";

@Module({
    imports: [
        TypeOrmModule.forFeature([OrderItemRepository, OrderRepository]),
    ],
    controllers: [OrderItemController, ManagementController],
    providers: [OrderItemService, OrderService],
    exports: [OrderItemService],
})
export class OrderItemModule {}
