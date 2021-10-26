import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { OrderItemService } from "../service/orderitem.service";
import { OrderItemController } from "../web/rest/orderitem.controller";
import { OrderItemRepository } from "../repository/orderitem.repository";
import { ManagementController } from '../web/rest/management.controller';

@Module({
    imports: [
        TypeOrmModule.forFeature([OrderItemRepository]),
    ],
    controllers: [OrderItemController, ManagementController],
    providers: [OrderItemService],
    exports: [OrderItemService],
})
export class OrderItemModule {}
