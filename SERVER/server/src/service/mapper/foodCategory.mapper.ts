import { FoodCategory } from '../../domain/foodCategory.entity';
import { FoodCategoryDTO } from '../dto/foodCategory.dto';

export class FoodCategoryMapper {

    static fromDTOtoEntity(foodCategoryDTO: FoodCategoryDTO): FoodCategory {
        if (!foodCategoryDTO) {
            return;
        }

        const foodCategory = new FoodCategory();
        const fileds = Object.getOwnPropertyNames(foodCategoryDTO);
        fileds.forEach(filed => {
            foodCategory[filed] = foodCategoryDTO[filed];
        });

        return foodCategory;
    }

    static fromEntityToDTO(foodCategory: FoodCategory): FoodCategoryDTO {
        if (!foodCategory) {
            return;
        }

        const foodCategoryDTO = new FoodCategoryDTO();
        const fileds = Object.getOwnPropertyNames(foodCategory);
        fileds.forEach(filed => {
            foodCategoryDTO[filed] = foodCategory[filed];
        });

        return foodCategoryDTO;
    }
    
}