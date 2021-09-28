import { Module } from '@nestjs/common';
import { ManagementController } from '../web/rest/management.controller';
import { UserRepository } from '../repository/user.repository';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthenticationController } from '../web/rest/auth/authentication.controller';
import { AuthenticationService } from '../service/auth/authentication.service';
import { UserService } from "../service/user.service";

@Module({
    imports: [TypeOrmModule.forFeature([UserRepository])],
    controllers: [AuthenticationController, ManagementController],
    providers: [AuthenticationService, UserService],
    exports: [AuthenticationService, UserService],
})

export class AuthenticationModule {}
