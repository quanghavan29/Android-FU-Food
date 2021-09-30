import { ApiModelProperty } from "@nestjs/swagger";
import { Type } from "class-transformer";
import { BaseDTO } from "./base.dto";

export class RestaurantDTO extends BaseDTO {

    @ApiModelProperty({ required: false })
    name?: string;

    @ApiModelProperty({ required: false })
    imageUrl?: string;

    @ApiModelProperty({ required: false })
    address?: string;

    @ApiModelProperty({ required: false })
    foods?: any[]; 
    
}