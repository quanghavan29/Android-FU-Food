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
import { Request } from '../../client/request';
import { LoggingInterceptor } from '../../client/interceptors/logging.interceptor';
import { ApiBearerAuth, ApiUseTags, ApiResponse, ApiOperation } from '@nestjs/swagger';
import { FoodCategoryService } from '../../service/foodCategory.service';
import { FoodCategoryDTO } from 'src/service/dto/foodCategory.dto';

@Controller('api/food-categories')
// @UseGuards(AuthGuard, RolesGuard)
@UseInterceptors(LoggingInterceptor, ClassSerializerInterceptor)
// @ApiBearerAuth()
@ApiUseTags('food-categories-resource')
export class FoodCategoryController {
    logger = new Logger('FoodCategoryController');

    constructor(private readonly foodCategoryService: FoodCategoryService) {}

    @Get('/get-all')
    // @Roles(RoleType.ADMIN)
    @ApiOperation({ title: 'Get the list of food categories' })
    @ApiResponse({
        status: 200,
        description: 'List all food categories',
        type: UserDTO,
    })
    async getAllFoodCategories(): Promise<FoodCategoryDTO[] | undefined> {
        return this.foodCategoryService.findAll();
    }

}
