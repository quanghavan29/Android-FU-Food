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

}
