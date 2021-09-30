import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { FoodDTO } from './dto/food.dto';
import { FoodMapper } from './mapper/food.mapper';
import { FoodRepository } from '../repository/food.repository';
import { FindManyOptions } from 'typeorm';

@Injectable()
export class FoodService {

    constructor(@InjectRepository(FoodRepository) private foodRepository: FoodRepository) {}

    async findById(id: string): Promise<FoodDTO | undefined> {
        const result = await this.foodRepository.findOne(id);
        return FoodMapper.fromEntityToDTO(result);
    }

    async findAll(): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find({
            relations: ['foodCategory', 'restaurant'],
        });
        const foodCategoriesDTO: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodCategoriesDTO.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodCategoriesDTO;
    }

    async findAllFoodsByFoodType(foodType: string): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find({
            relations: ['foodCategory', 'restaurant'],
            where: { foodCategory: {
                id: foodType,
            }}
        });
        const foodCategoriesDTO: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodCategoriesDTO.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodCategoriesDTO;
    }

    async findTop10BestSellingFoods(): Promise<FoodDTO[] | undefined> {
        const resultList = await this.foodRepository.find(
            {
                order: {
                    salesQuantity: 'DESC'
                },
                take: 10,
            });
        const foodCategoriesDTO: FoodDTO[] = [];
        if (resultList) {
            resultList.forEach(food => foodCategoriesDTO.push(FoodMapper.fromEntityToDTO(food)));
        }
        return foodCategoriesDTO;
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
