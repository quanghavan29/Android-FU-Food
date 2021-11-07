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
import { OrderService } from '../../service/order.service';
import { OrderDTO } from '../../service/dto/order.dto';
import { OrderItemService } from '../../service/orderitem.service';

@Controller('api/orders')
// @UseGuards(AuthGuard, RolesGuard)
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
// @ApiBearerAuth()
@ApiUseTags('orders-resource')
export class OrderController {
    logger = new Logger('OrderController');
    
    constructor(private readonly orderService: OrderService,
        private readonly orderItemService: OrderItemService) {}

    @Post('/check-out')
    @ApiOperation({ title: 'Create order' })
    @ApiResponse({
        status: 201,
        description: 'The record has been successfully created.',
        type: OrderDTO,
    })
    async createOrder(@Body() carts: any[], @Req() req: Request): Promise<any> {
        let userId = req.query.userId;
        let address = req.query.address;
        let totalAmount = req.query.totalAmount;
        let totalQuantity = req.query.totalQuantity;

        console.log('address ', address);
        console.log('userId ', userId);
        console.log('totalAmount ', totalAmount);
        console.log('carts ', carts);

        let orderDTO: OrderDTO = {
            id: (new Date()).getTime().toString(),
            user: {
                id: userId,
            },
            orderedDate: new Date(),
            address: address,
            totalAmount: totalAmount,
            totalQuantity: totalQuantity,
            totalItem: carts.length,
            status: 'pending',
        }

        const createdOrder = await this.orderService.save(orderDTO);

        await this.orderItemService.saveAllItemOfOrder(carts, createdOrder);

        return createdOrder;
    }
  
    @Get('/get-orders-of-user')
    // @Roles(RoleType.ADMIN)
    @ApiOperation({ title: 'Get the list of orders' })
    @ApiResponse({
        status: 200,
        description: 'List orders of user',
        type: OrderDTO,
    })
    async getAllFoodsOfRestaurant(@Req() req: Request): Promise<OrderDTO[] | undefined> {
        let userId = req.query.userId;
        return await this.orderService.getListOrdersOfUser(userId);
    }

    @Get('/get-all-orders')
    @ApiOperation({ title: 'Get the list of orders' })
    @ApiResponse({
        status: 200,
        description: 'List orders of user',
        type: OrderDTO,
    })
    async getAllOrder(): Promise<OrderDTO[] | undefined> {
        return await this.orderService.getAllOrders();
    }

}
