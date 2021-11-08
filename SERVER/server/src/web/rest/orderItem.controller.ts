import {
    Controller,
    Get,
    Logger,
    UseGuards,
    Req,
    UseInterceptors,
    ClassSerializerInterceptor,
    Post,
    Body,
} from '@nestjs/common';
import { Request } from 'express';
import { LoggingInterceptor } from '../../client/interceptors/logging.interceptor';
import { ApiBearerAuth, ApiUseTags, ApiResponse, ApiOperation } from '@nestjs/swagger';
import { OrderItemService } from '../../service/orderitem.service';
import { OrderItemDTO } from '../../service/dto/orderitem.dto';

@Controller('api/orderitems')
// @UseGuards(AuthGuard, RolesGuard)
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
// @ApiBearerAuth()
@ApiUseTags('orderitems-resource')
export class OrderItemController {
    logger = new Logger('OrderItemController');
    
    constructor(private readonly orderitemService: OrderItemService) {}

    @Get('/get-order-detail-by-user')
    // @Roles(RoleType.ADMIN)
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: OrderItemDTO,
    })
    async getAllFoodsOfRestaurant(@Req() req: Request): Promise<any | undefined> {
        let userId = req.query.userId;
        let orderId = req.query.orderId;
        console.log(userId, orderId);
        return this.orderitemService.getOrdersDetailByUser(userId, orderId);
    }

    @Get('/get-order-detail')
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: OrderItemDTO,
    })
    async getOrderDetail(@Req() req: Request): Promise<any | undefined> {
        let orderId = req.query.orderId;
        console.log(orderId);
        return this.orderitemService.getOrdersDetail(orderId);
    }

}
