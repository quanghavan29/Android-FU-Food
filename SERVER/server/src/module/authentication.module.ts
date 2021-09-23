import { Module } from '@nestjs/common';
import { ManagementController } from '../web/rest/management.controller';
import { UserRepository } from '../repository/user.repository';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthenticationController } from '../web/rest/authentication/authenticationcontroller';
import { FacebookStrategy } from '../strategy/facebook.strategy';
import { AuthenticationService } from '../service/auth/authentication.service';

@Module({
    imports: [TypeOrmModule.forFeature([UserRepository])],
    controllers: [AuthenticationController, ManagementController],
    providers: [AuthenticationService, FacebookStrategy],
    exports: [],
})

export class AuthenticationModule {}
