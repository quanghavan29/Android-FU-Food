import { Food } from '../../domain/food.entity';
import { FoodDTO } from '../dto/food.dto';

export class FoodMapper {

    static fromDTOtoEntity(foodDTO: FoodDTO): Food {
        if (!foodDTO) {
            return;
        }

        const food = new Food();
        const fileds = Object.getOwnPropertyNames(foodDTO);
        fileds.forEach(filed => {
            food[filed] = foodDTO[filed];
        });

        return food;
    }

    static fromEntityToDTO(food: Food): FoodDTO {
        if (!food) {
            return;
        }

        const foodDTO = new FoodDTO();
        const fileds = Object.getOwnPropertyNames(food);
        fileds.forEach(filed => {
            foodDTO[filed] = food[filed];
        });

        return foodDTO;
    }
    
}