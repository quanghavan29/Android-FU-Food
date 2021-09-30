import { ApiModelProperty } from "@nestjs/swagger";
import { Type } from "class-transformer";
import { BaseDTO } from "./base.dto";

export class FoodDTO extends BaseDTO {

    @ApiModelProperty({ required: false })
    name?: string;

    @ApiModelProperty({ required: false })
    imageUrl?: string;

    @ApiModelProperty({ required: false })
    price?: number;

    @ApiModelProperty({ required: false })
    salesQuantity?: number;

    @ApiModelProperty({ required: false })
    foodCategory?: any;

    @ApiModelProperty({ required: false })
    restaurant?: any;
    
    @ApiModelProperty({ required: false })
    reviewPoint?: number;

    @ApiModelProperty({ required: false })
    numberOfReview?: number;
}