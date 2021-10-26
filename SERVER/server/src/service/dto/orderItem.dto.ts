import { ApiModelProperty } from "@nestjs/swagger";
import { BaseDTO } from "./base.dto";

export class OrderItemDTO extends BaseDTO {

    @ApiModelProperty({ required: false })
    order?: any;

    @ApiModelProperty({ required: false })
    food?: any;

    @ApiModelProperty({ required: false })
    subAmount?: number;

    @ApiModelProperty({ required: false })
    quantity?: number;
    
}