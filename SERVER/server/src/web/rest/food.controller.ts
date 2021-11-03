import {
    Controller,
    Get,
    Logger,
    UseGuards,
    Req,
    UseInterceptors,
    ClassSerializerInterceptor,
    Body,
} from '@nestjs/common';
import { Request } from 'express';
import { LoggingInterceptor } from '../../client/interceptors/logging.interceptor';
import { ApiBearerAuth, ApiUseTags, ApiResponse, ApiOperation } from '@nestjs/swagger';
import { FoodService } from '../../service/food.service';
import { FoodDTO } from '../../service/dto/food.dto';
import { UserDTO } from '../../service/dto/user.dto';

@Controller('api/foods')
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
@ApiUseTags('foods-resource')
export class FoodController {
    logger = new Logger('FoodController');

    constructor(private readonly foodService: FoodService) {}

    @Get('/get-top10-best-selling-foods')
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: FoodDTO,
    })
    async getTop10BestSellingFoods(): Promise<FoodDTO[] | undefined> {

        return this.foodService.findTop10BestSellingFoods();
    }

    @Get('/get-all')
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: FoodDTO,
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

    @Get('/get-food-detail')
    @ApiOperation({ title: 'Get food detail' })
    @ApiResponse({
        status: 200,
        description: 'food detail',
        type: FoodDTO,
    })
    async getFoodById(@Req() req: Request): Promise<FoodDTO | undefined> {
        let foodId = req.query.foodId;
        return this.foodService.findById(foodId);
    }

    @Get('/get-all-food-of-restaurant')
    @ApiOperation({ title: 'Get the list of foods' })
    @ApiResponse({
        status: 200,
        description: 'List all foods',
        type: FoodDTO,
    })
    async getAllFoodsOfRestaurant(@Req() req: Request): Promise<FoodDTO[] | undefined> {
        let restaurantId = req.query.restaurantId;
        let foodId = req.query.foodId;
        console.log(foodId, restaurantId);
        return this.foodService.findAllFoodsByRestaurantId(restaurantId, foodId);
    }

}
