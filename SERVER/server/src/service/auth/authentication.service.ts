import { Injectable, Logger } from "@nestjs/common";

@Injectable()
export class AuthenticationService {
    logger = new Logger('AuthenticationService');
}