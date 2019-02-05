import { UserDetails } from "./user-details";
import { UserAddress } from "./user-address";
export class User{
    constructor(
        public userId: string,
        public firstName: string,
        public lastName: string,
        public login: string,
        public emailPollub: string,
        public userDetails: UserDetails,
        public userAddress: UserAddress
    ){}
}