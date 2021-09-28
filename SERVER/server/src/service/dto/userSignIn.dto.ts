import { ApiModelProperty } from "@nestjs/swagger";
import { IsString } from "class-validator";

export class UserSignInDTO {
    @ApiModelProperty({ description: 'User password' })
    @IsString()
    readonly password: string;

    @ApiModelProperty({ description: 'User login name' })
    @IsString()
    readonly phone: string;
}
