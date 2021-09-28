import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { FoodCategoryDTO } from './dto/foodCategory.dto';
import { FoodCategoryMapper } from './mapper/foodCategory.mapper';
import { FoodCategoryRepository } from '../repository/foodCategory.repository';
import { FindManyOptions } from 'typeorm';

@Injectable()
export class FoodCategoryService {

    constructor(@InjectRepository(FoodCategoryRepository) private foodCategoryRepository: FoodCategoryRepository) {}

    async findById(id: string): Promise<FoodCategoryDTO | undefined> {
        const result = await this.foodCategoryRepository.findOne(id);
        return FoodCategoryMapper.fromEntityToDTO(result);
    }

    async findAll(): Promise<FoodCategoryDTO[] | undefined> {
        const resultList = await this.foodCategoryRepository.find();
        const foodCategoriesDTO: FoodCategoryDTO[] = [];
        if (resultList) {
            resultList.forEach(foodCategory => foodCategoriesDTO.push(FoodCategoryMapper.fromEntityToDTO(foodCategory)));
        }
        return foodCategoriesDTO;
    }

    async findAndCount(options: FindManyOptions<FoodCategoryDTO>): Promise<[FoodCategoryDTO[], number]> {
        const resultList = await this.foodCategoryRepository.findAndCount(options);
        const foodCategorysDTO: FoodCategoryDTO[] = [];
        if (resultList && resultList[0]) {
            resultList[0].forEach(foodCategory => foodCategorysDTO.push(FoodCategoryMapper.fromEntityToDTO(foodCategory)));
            resultList[0] = foodCategorysDTO;
        }
        return resultList;
    }

    async delete(FoodCategoryDTO: FoodCategoryDTO): Promise<FoodCategoryDTO | undefined> {
        const FoodCategory = FoodCategoryMapper.fromDTOtoEntity(FoodCategoryDTO);
        const result = await this.foodCategoryRepository.remove(FoodCategory);
        return FoodCategoryMapper.fromEntityToDTO(result);
    }

}
