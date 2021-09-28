import { HttpException, HttpStatus, Injectable, Logger } from "@nestjs/common";
import { JwtService } from "@nestjs/jwt";
import { InjectRepository } from "@nestjs/typeorm";
import { AuthorityRepository } from "../../repository/authority.repository";
import { UserService } from "../user.service";
import * as bcrypt from 'bcrypt';
import { UserSignInDTO } from "../dto/userSignIn.dto";
import { UserDTO } from "../dto/user.dto";
import { UserRepository } from "../../repository/user.repository";

@Injectable()
export class AuthenticationService {
    logger = new Logger('AuthenticationService');

    constructor(
        // private readonly jwtService: JwtService,
        // @InjectRepository(AuthorityRepository) private authorityRepository: AuthorityRepository,
        @InjectRepository(UserRepository) private userRepository: UserRepository,
        private userService: UserService,
    ) { }

    async signIn(userSignIn: UserSignInDTO): Promise<any> {
        const signInPhone = userSignIn.phone;
        const signInpassword = userSignIn.password;

        const userFind = await this.userService.findByPhone(signInPhone);

        let statusCode = 200;
        let errorMessage = 'Sign in success!';

        if (!userFind) {
            statusCode = 400;
            errorMessage = 'Phone do not registered!';
        } else {
            const validPassword = !!userFind && (await bcrypt.compare(signInpassword, userFind.password));
            if (!validPassword) {
                statusCode = 400;
                errorMessage = 'Invalid password!';
            }
        }

        // if (userFind && !userFind.activated) {
        //     throw new HttpException('Your account is not been activated!', HttpStatus.BAD_REQUEST);
        // }

        return {
            user: userFind,
            statusCode: statusCode,
            errorMessage: errorMessage,
        };

        // const user = await this.findUserWithAuthById(userFind.id);

        // const payload: Payload = { id: user.id, username: user.login, authorities: user.authorities };

        // /* eslint-disable */
        // return {
        //     id_token: this.jwtService.sign(payload),
        // };
    }

    async findUserWithAuthById(userId: string): Promise<UserDTO | undefined> {
        const userDTO: UserDTO = await this.userService.findByFields({ where: { id: userId } });
        return userDTO;
    }
}