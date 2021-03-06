import { ApiModelProperty } from "@nestjs/swagger";
import { BaseDTO } from "./base.dto";

export class FoodCategoryDTO extends BaseDTO {

    @ApiModelProperty({ required: false })
    name?: string;

    @ApiModelProperty({ required: false })
    imageUrl?: string;
    
    @ApiModelProperty({ required: false })
    foods?: any[]; 
}