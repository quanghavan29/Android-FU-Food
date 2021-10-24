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

@Controller('api/orders')
// @UseGuards(AuthGuard, RolesGuard)
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
// @ApiBearerAuth()
@ApiUseTags('orders-resource')
export class OrderController {
    logger = new Logger('OrderController');
    
    constructor(private readonly orderService: OrderService) {}

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
            user: {
                id: userId,
            },
            orderedDate: new Date(),
            address: address,
            totalAmount: totalAmount,
            totalQuantity: totalQuantity,
            totalItem: carts.length
        }

        const createdOrder = await this.orderService.save(orderDTO);

        return createdOrder;
    }

}
