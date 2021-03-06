import { Controller, Req, UseInterceptors, Logger, Post, Body } from "@nestjs/common";
import { ApiOperation, ApiResponse, ApiUseTags } from "@nestjs/swagger";
import { Request } from "express";
import { UserSignInDTO } from "../../../service/dto/auth/userSignIn.dto";
import { LoggingInterceptor } from "../../../client/interceptors/logging.interceptor";
import { AuthenticationService } from "../../../service/auth/authentication.service";
import { UserSignUpDTO } from "../../../service/dto/auth/userSignUp.dto";

@Controller('api')
@UseInterceptors(LoggingInterceptor)
@ApiUseTags('authentication-controller')
export class AuthenticationController {

    logger = new Logger('AuthenticationController');

    constructor(private readonly authenticationService: AuthenticationService) {}

    @Post('/authenticate')
    @ApiOperation({ title: 'Authorization api retrieving token' })
    @ApiResponse({
        status: 201,
        description: 'Authorized',
    })
    async authorize(@Body() userSignin: UserSignInDTO): Promise<any> {
        return await this.authenticationService.signIn(userSignin);
    }

    @Post('/sign-up')
    @ApiOperation({ title: 'Authorization api retrieving token' })
    @ApiResponse({
        status: 201,
        description: 'Authorized',
    })
    async signUp(@Body() userSignUp: UserSignUpDTO): Promise<any> {
        return await this.authenticationService.signUp(userSignUp);
    }
 
}