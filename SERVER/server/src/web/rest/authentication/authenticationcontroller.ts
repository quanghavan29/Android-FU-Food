import { Controller, Get, UseGuards, HttpStatus, Req } from "@nestjs/common";
import { AuthGuard } from "@nestjs/passport";
import { Request } from "express";
import { initializeApp } from 'firebase/app';
import { getFirestore, collection, getDocs } from 'firebase/firestore/lite';

// import { AppService } from "./app.service";

@Controller()
export class AuthenticationController {
    // constructor(private readonly appService: AppService) { }

    // @Get()
    // getHello(): string {
    //     return this.appService.getHello();
    // }

    @Get("/facebook")
    @UseGuards(AuthGuard("facebook"))
    async facebookLogin(): Promise<any> {
        return HttpStatus.OK;
    }

    @Get("/facebook/redirect")
    @UseGuards(AuthGuard("facebook"))
    async facebookLoginRedirect(@Req() req: Request): Promise<any> {
        console.log(req.user);
        return {
            statusCode: HttpStatus.OK,
            data: req.user,
        };
    }

    @Get("/firsebase")
    async next(@Req() req: Request): Promise<any> {
        // Follow this pattern to import other Firebase services
        // import { } from 'firebase/<service>';
        
        // TODO: Replace the following with your app's Firebase project configuration
        const firebaseConfig = {
          //...
        };
        
        const app = initializeApp(firebaseConfig);
        const db = getFirestore(app);
        
        // Get a list of cities from your database
        async function getCities(db) {
          const citiesCol = collection(db, 'cities');
          const citySnapshot = await getDocs(citiesCol);
          const cityList = citySnapshot.docs.map(doc => doc.data());
          return cityList;
        }
    }
}