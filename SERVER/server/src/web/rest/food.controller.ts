import {
    Controller,
    Get,
    Logger,
    UseGuards,
    Req,
    UseInterceptors,
    ClassSerializerInterceptor,
} from '@nestjs/common';
import { UserDTO } from '../../service/dto/user.dto';
import { Request } from 'express';
import { LoggingInterceptor } from '../../client/interceptors/logging.interceptor';
import { ApiBearerAuth, ApiUseTags, ApiResponse, ApiOperation } from '@nestjs/swagger';
import { FoodService } from '../../service/food.service';
import { FoodDTO } from 'src/service/dto/food.dto';

@Controller('api/foods')
// @UseGuards(AuthGuard, RolesGuard)
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
// @ApiBearerAuth()
@ApiUseTags('foods-resource')
export class FoodController {
    logger = new Logger('FoodController');
s
    constructor(private readonly foodService: FoodService) {}

    @Get('/get-top10-best-selling-foods')
    // @Roles(RoleType.ADMIN)
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: UserDTO,
    })
    async getTop10BestSellingFoods(): Promise<FoodDTO[] | undefined> {

        return this.foodService.findTop10BestSellingFoods();
    }

    @Get('/get-all')
    // @Roles(RoleType.ADMIN)
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: UserDTO,
    })
    async getAllFoods(@Req() req: Request): Promise<FoodDTO[] | undefined> {
        let foodType = req.query.foodType;
        // get all food
        if (foodType == 'allFood' || !foodType) {
            return this.foodService.findAll();
        } else {
            // get food by food type
            return this.foodService.findAllFoodsByFoodType(foodType);
        }

    }

}
