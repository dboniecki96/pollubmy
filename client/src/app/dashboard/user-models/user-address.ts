export class UserAddress{
    constructor(
        public userAddressId: string,
        public city: string,
        public street: string,
        public houseNumber: number,
        public apartmentNumber: number,
        public postalCode: string,
        public country: string
    ){}
}