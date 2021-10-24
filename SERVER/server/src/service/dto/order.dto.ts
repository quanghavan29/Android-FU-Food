import { ApiModelProperty } from "@nestjs/swagger";
import { BaseDTO } from "./base.dto";

export class OrderDTO extends BaseDTO {

    @ApiModelProperty({ required: false })
    user?: any;

    @ApiModelProperty({ required: false })
    orderedDate?: Date;

    @ApiModelProperty({ required: false })
    address?: string;

    @ApiModelProperty({ required: false })
    totalAmount?: number; 
    
    @ApiModelProperty({ required: false })
    totalQuantity?: number;
    
    @ApiModelProperty({ required: false })
    totalItem?: number;
    
}