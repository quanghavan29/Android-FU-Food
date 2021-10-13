import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { FoodDTO } from './dto/food.dto';
import { FoodMapper } from './mapper/food.mapper';
import { FoodRepository } from '../repository/food.repository';
import { FindManyOptions, Not } from 'typeorm';
import { Equals, EQUALS } from 'class-validator';

@Injectable()
export class FoodService {

    constructor(@InjectRepository(FoodRepository) private foodRepository: FoodRepository) {}

    async findById(id: string): Promise<FoodDTO | undefined> {
        const result = await this.foodRepository.findOne(id, {
            relations: ['foodCategory', 'restaurant'],
        });
        return FoodMapper.fromEntityToDTO(result);
    }

    async findAll(): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find({
            relations: ['foodCategory', 'restaurant'],
        });
        const foodDTOs: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodDTOs.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodDTOs;
    }

    async findAllFoodsByFoodType(foodType: string): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find({
            relations: ['foodCategory', 'restaurant'],
            where: { foodCategory: {
                id: foodType,
            }}
        });
        const foodDTOs: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodDTOs.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodDTOs;
    }

    async findTop10BestSellingFoods(): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find(
            {
                relations: ['restaurant'],
                order: {
                    salesQuantity: 'DESC'
                },
                take: 10,
            });
        const foodDTOs: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodDTOs.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodDTOs;
    }

    async findAllFoodsByRestaurantId(restaurantId: string, foodId: string): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find({
            relations: ['foodCategory', 'restaurant'],
            where: { 
                restaurant: {
                id: restaurantId,
                }, 
                id: Not(foodId),
            }
        });
        const foodDTOs: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodDTOs.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodDTOs;
    }


    async findAndCount(options: FindManyOptions<FoodDTO>): Promise<[FoodDTO[], number]> {
        const resultList = await this.foodRepository.findAndCount(options);
        const foodsDTO: FoodDTO[] = [];
        if (resultList && resultList[0]) {
            resultList[0].forEach(food => foodsDTO.push(FoodMapper.fromEntityToDTO(food)));
            resultList[0] = foodsDTO;
        }
        return resultList;
    }

    async delete(FoodDTO: FoodDTO): Promise<FoodDTO | undefined> {
        const Food = FoodMapper.fromDTOtoEntity(FoodDTO);
        const result = await this.foodRepository.remove(Food);
        return FoodMapper.fromEntityToDTO(result);
    }

}
