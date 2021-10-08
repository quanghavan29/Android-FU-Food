import { ApiModelProperty } from "@nestjs/swagger";

export class UserSignUpDTO {
    @ApiModelProperty({ description: 'User full name' })
    fullName: string;

    @ApiModelProperty({ description: 'User phone' })
    phone: string;

    @ApiModelProperty({ description: 'User password' })
    password: string;
}